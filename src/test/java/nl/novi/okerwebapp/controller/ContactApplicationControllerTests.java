package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.service.ContactApplicationService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ContactApplicationController.class)
@ContextConfiguration(classes={ContactApplicationController.class})
public class ContactApplicationControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactApplicationService contactApplicationService;


    @MockBean
    JavaMailSender mailSender;

    @Test
    @WithMockUser(username = "ADMIN", authorities = {"ADMIN", "USER"})
    public void testEndpointContactApplication() throws Exception {
        //Arrange

        //Act
        doNothing().when(mailSender).send(any(SimpleMailMessage.class));

        //Assert
        mvc.perform(post("/contactapplications")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf().asHeader())
                        .content("{\"name\":\"Danielle van Manen\",\"telephoneNumber\":\"0639861015\",\"question\":\"Kunnen krankzinnige krokodillen knalgele kanaries kietelen?\",\"emailAddress\":\"danielleoker@gmail.com\"}"))
                .andExpect(status().isNoContent());
    }
}
