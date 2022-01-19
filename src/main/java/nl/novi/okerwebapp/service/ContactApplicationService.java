package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.ContactApplicationPostRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class ContactApplicationService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendContactApplication(ContactApplicationPostRequestDto contactApplicationPostRequestDto) {
        String body = "Hallo,\n" +
                "Deze bezoeker heeft een contactverzoek ingediend:\n" +
                "Naam: " + contactApplicationPostRequestDto.getName() + "\n" +
                "Telefoonnummer: " + contactApplicationPostRequestDto.getTelephoneNumber() + "\n" +
                "Vraag: " + contactApplicationPostRequestDto.getQuestion() + "\n" +
                "Email-adres: " + contactApplicationPostRequestDto.getEmailAddress();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Contactaanvraag");
        mail.setText(body);
        mail.setTo("danielleoker@gmail.com");
        mailSender.send(mail);
        return;
    }
}
