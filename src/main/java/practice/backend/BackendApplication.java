package practice.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import practice.backend.service.email.EmailSenderService;

@SpringBootApplication
public class BackendApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("Application is running on port 8080.");
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void sendMail(){
//        emailSenderService.sendEmail("okoroaforkelechi123@gmail.com", "ZIPDEMON email sender",
//                "");
//    }

}
