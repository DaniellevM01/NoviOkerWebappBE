package nl.novi.okerwebapp.service;

import nl.novi.okerwebapp.dto.requests.ContactApplicationPostRequestDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ContactApplicationServiceTest {

    @Mock
    JavaMailSender mailSender;

    @InjectMocks
    ContactApplicationService contactApplicationService;

    @Captor
    ArgumentCaptor<SimpleMailMessage> mailCaptor;

    @Test
    public void sendContactApplication(){
        //Arrange
        ContactApplicationPostRequestDto contactApplicationPostRequestDto = new ContactApplicationPostRequestDto();
        contactApplicationPostRequestDto.setName("Danielle");
        contactApplicationPostRequestDto.setEmailAddress("danielleoker@gmail.com");
        contactApplicationPostRequestDto.setQuestion("Geen");
        contactApplicationPostRequestDto.setTelephoneNumber("09009696");

        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        //Act
        contactApplicationService.sendContactApplication(contactApplicationPostRequestDto);
        Mockito.verify(mailSender).send(mailCaptor.capture());


        SimpleMailMessage mail_message = mailCaptor.getValue();
                //Assert
        assertTrue(mail_message.getText().contains(contactApplicationPostRequestDto.getQuestion()));
        assertTrue(mail_message.getText().contains(contactApplicationPostRequestDto.getTelephoneNumber()));

    }
}