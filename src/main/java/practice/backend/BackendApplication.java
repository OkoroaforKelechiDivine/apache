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

    @EventListener(ApplicationReadyEvent.class)
    public void sendMail(){
        emailSenderService.sendEmail("nonsookoroafor@gmail.com", "ZIPDEMON email sender",
                "Brother Nonso, I wanted to let you know that I can still remember your email. And thank you for coming today. I put everything you've told me today into practice.");
    }

}
