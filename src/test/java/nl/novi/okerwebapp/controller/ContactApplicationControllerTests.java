package nl.novi.okerwebapp.controller;

import nl.novi.okerwebapp.service.ContactApplicationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

public class ContactApplicationControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactApplicationService contactApplicationService;

    //@Test
    //public void testEndpointContactApplication() throws Exception {

    //}
}
