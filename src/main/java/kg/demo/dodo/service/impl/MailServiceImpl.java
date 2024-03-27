package kg.demo.dodo.service.impl;

import kg.demo.dodo.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public String send(String to, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(from);

        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject("Security");
        simpleMailMessage.setText(text);

        mailSender.send(simpleMailMessage);

        return "OK";
    }
}
