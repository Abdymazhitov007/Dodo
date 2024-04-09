package kg.demo.dodo.service.impl;

import kg.demo.dodo.exceptions.IncorrectEmailException;
import kg.demo.dodo.exceptions.PasswordException;
import kg.demo.dodo.model.dto.AccountDTO;
import kg.demo.dodo.model.dto.UserDTO;
import kg.demo.dodo.model.requests.AuthRequest;
import kg.demo.dodo.model.requests.ValidateEmailReq;
import kg.demo.dodo.model.response.UserInfoResponse;
import kg.demo.dodo.service.*;
import kg.demo.dodo.util.JwtProvider;
import kg.demo.dodo.util.Language;
import kg.demo.dodo.util.ResourceBundleLanguage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final AccountService accountService;
    private final UserService userService;
    private final OrderService orderService;
    private final MailService mailService;

    private final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private final Pattern pattern = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean isValidEmail(String email) {
        return !pattern.matcher(email).matches();
    }

    @Override
    public String auth(AuthRequest request, int lang) {

        if (isValidEmail(request.getEmail())) {
            throw new IncorrectEmailException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "incorrectEmail"));
        }

        AccountDTO accountDTO = accountService.findByEmail(request.getEmail());

        Random random = new Random();
        int tempPsw = 100000 + random.nextInt(900000);
        String messageToSend = "your temporary password is " + String.valueOf(tempPsw);

        if (accountDTO == null) {
            mailService.send(request.getEmail(), messageToSend);

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
            newUser.setDodoCoins(0.0);

            userService.save(newUser);

        } else {
            mailService.send(request.getEmail(), messageToSend);
            accountDTO.setTempPassword(tempPsw);
            accountDTO.setApproved(false);
            accountDTO.setDateTimeOfPassword(LocalDateTime.now());
            accountService.update(accountDTO);

        }

        return ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "ready");

    }

    @Override
    public String validate(ValidateEmailReq request, int lang) {

        if (isValidEmail(request.getEmail())) {
            throw new IncorrectEmailException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "incorrectEmail"));
        }

        AccountDTO accountDTO = accountService.findByEmail(request.getEmail());

        if (request.getTempPassword().equals(accountDTO.getTempPassword()) && !accountDTO.isApproved()) {
            if (Duration.between(accountDTO.getDateTimeOfPassword(), LocalDateTime.now()).toMinutes() <= 5) {

                accountDTO.setApproved(true);
                accountService.update(accountDTO);
                UserDTO userDTO = userService.getByAccountId(accountDTO.getId());

                return jwtProvider.generateAccessToken(userDTO.getId());

            } else
                throw new PasswordException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "passwordExpired"));
        } else
            throw new PasswordException(ResourceBundleLanguage.periodMessage(Language.getLanguage(lang), "incorrectPassword"));


    }



    @Override
    public Long getUserIdByToken(String accessToken, int lang) {
        return jwtProvider.validateToken(accessToken, lang);
    }

    @Override
    public UserInfoResponse getUserInfoByToken(String accessToken, int lang) {

        UserDTO userDTO = userService.findById(getUserIdByToken(accessToken, lang), lang);

        UserInfoResponse response = new UserInfoResponse();
        response.setEmail(userDTO.getAccount().getEmail());
        response.setDodoCoins(userDTO.getDodoCoins());
        response.setName(userDTO.getName());
        response.setNumOfAddress(userService.getNumOfAddressByUserId(userDTO.getId()));
        response.setNumOfOrders(orderService.getNumOfOrderByUserId(userDTO.getId()));

        return response;
    }


}
