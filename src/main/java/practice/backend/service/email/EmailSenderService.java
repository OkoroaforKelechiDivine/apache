package practice.backend.service.email;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;

@Service
@Slf4j
public class EmailSenderService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOTP(String toEmail, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("okoroaforkelechi123@gmail.com");
        message.setTo(toEmail);
//        message.setSentDate(Date.from(Instant.parse(LocalDate.now().toString())));
        message.setText("This is your OTP: " + generateOTP() + "\nUse this OTP to verify your account.");
        message.setSubject(subject);

        mailSender.send(message);
        log.info("Mail successfully sent.");
    }

    public String generateOTP(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        String strings = "";
        int OTP_Length = 10;

        Random random = new Random();
        char[] text = new char[OTP_Length];
        for (int index = 0; index < OTP_Length; index++){
            text[index] = characters.charAt(random.nextInt(characters.length()));
        }
        for (int i = 0; i < text.length; i++){
            strings += text[i];
        }
        return strings;
    }
}
