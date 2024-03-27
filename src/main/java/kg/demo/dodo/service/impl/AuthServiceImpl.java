package kg.demo.dodo.service.impl;

import kg.demo.dodo.model.dto.AccountDTO;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;
import kg.demo.dodo.service.AccountService;
import kg.demo.dodo.service.AuthService;
import kg.demo.dodo.service.MailService;
import kg.demo.dodo.service.UserService;
import kg.demo.dodo.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final UserService userService;
    private final MailService mailService;

    @Override
    public String auth(AuthRequest request) {

        //найти в базе данных в таблице accounts запись по email
        AccountDTO accountDTO = accountService.findByEmail(request.getEmail());

        Random random = new Random();
        int tempPsw = 100000 + random.nextInt(900000);

        //если нет записи то дальше
        //отправляем temp password на почту  temp password(567-123)
        //создаем Account, сетим данные ( email, temp password, status new , add date, update date ) сохраняем account entity
        //создаем User entity сетим все данные

        if (accountDTO == null) {
            mailService.send(request.getEmail(), "your temporary password is " + String.valueOf(tempPsw));

            AccountDTO newAccount = new AccountDTO();
            newAccount.setEmail(request.getEmail());
            newAccount.setApproved(false);
            newAccount.setDateTimeOfPassword(LocalDateTime.now());
            newAccount.setTempPassword(tempPsw);

            accountService.save(newAccount);

            UserDTO newUser = new UserDTO();

            newUser.setName(request.getName());
            newUser.setPhone(request.getPhone());
            newUser.setAccount(newAccount);

            userService.save(newUser);

        }

        /*если запись есть
        //отправляем temp password на почту  temp password(567-123)
        находим аккаунт обновляем temp password
        * */

        else {
            mailService.send(request.getEmail(), "your temporary password is " + String.valueOf(tempPsw));
            accountDTO.setTempPassword(tempPsw);
            accountDTO.setApproved(false);
            accountDTO.setDateTimeOfPassword(LocalDateTime.now());
            accountService.update(accountDTO);

        }

        return "OK";

    }

    @Override
    public String validate(ValidateEmailReq request) {
        //найти в базе данных в таблице accounts запись по email
        AccountDTO accountDTO = accountService.findByEmail(request.getEmail());
        //сравнение пароля из реквеста и из бд
        if (request.getPassword().equals(accountDTO.getTempPassword())) {
            if (Duration.between(accountDTO.getDateTimeOfPassword(), LocalDateTime.now()).toMinutes() <= 1) {

                accountDTO.setApproved(true);
                accountService.update(accountDTO);
                UserDTO userDTO = userService.getByAccountId(accountDTO.getId());

                return jwtProvider.generateAccessToken(userDTO.getId());

            } else throw new RuntimeException("your password has expired");
        } else throw new RuntimeException("Incorrect password");
        // если время отправленного пароля истекло (дается 10 мин) то кидаем ошибку
        //если пароль неверный то кидаем ошибку
        //если не прошло 10 минут и пароль верны идем дальше
        //делаем статус у account (status approved)
        //найти юзера по аккаунту
        //формируем токен из userId и роли
        //возвращаем токен


    }

}
