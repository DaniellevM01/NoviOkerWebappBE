package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.service.VacancyApplicationService;
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

@WebMvcTest(VacancyApplicationController.class)
@ContextConfiguration(classes={VacancyApplicationController.class})
public class VacancyApplicationControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private VacancyApplicationService vacancyApplicationService;

    @Test
    @WithMockUser(username = "ADMIN", authorities = {"ADMIN", "USER"})
    public void testPatchVacancyApplication() throws Exception {
        //Arrange
        User user = new User();
        VacancyApplication vacancyApplication = new VacancyApplication();
        vacancyApplication.setStatus("aanvaard");
        vacancyApplication.setDescription("Hey hallo");
        //Act

        //Assert
        mvc.perform(patch("/vacancyapplications/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(csrf().asHeader())
                        .content("{\"status\": \"daan\"}"))
                .andExpect(status().isNoContent());
    }
}