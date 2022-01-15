package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.ContactApplicationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class ContactApplicationService {

    public void sendContactApplication(ContactApplicationRequestDto contactApplicationRequestDto) {
        String body = "Hallo,\n" +
                "Deze bezoeker heeft een contactverzoek ingediend:\n" +
                "Naam: " + contactApplicationRequestDto.getName() + "\n" +
                "Telefoonnummer: " + contactApplicationRequestDto.getTelephoneNumber() + "\n" +
                "Vraag: " + contactApplicationRequestDto.getQuestion() + "\n" +
                "Email-adres: " + contactApplicationRequestDto.getEmailAddress();

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setSubject("Contactaanvraag");
        mail.setText(body);
        mail.setTo("danielleoker@gmail.com");
        return;
    }
}
