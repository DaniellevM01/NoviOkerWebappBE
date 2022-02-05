package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.model.OfferApplication;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.service.OfferApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OfferApplicationController.class)
@ContextConfiguration(classes={OfferApplicationController.class})
public class OfferApplicationControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OfferApplicationService offerApplicationService;

    @Test
    @WithMockUser(username = "ADMIN", authorities = {"ADMIN", "USER"})
    public void testPatchOfferApplication() throws Exception {
        //Arrange
        User user = new User();
        OfferApplication offerApplication = new OfferApplication();
        offerApplication.setStatus("aanvaard");
        offerApplication.setDescription("Hey hallo");
        //Act

        //Assert
        mvc.perform(patch("/offerapplications/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf().asHeader())
                        .content("{\"status\": \"daan\"}"))
                .andExpect(status().isNoContent());
    }
}
