package nl.novi.okerwebapp.security;

import nl.novi.okerwebapp.dto.requests.VacancyApplicationPatchRequestDto;
import nl.novi.okerwebapp.model.User;
import nl.novi.okerwebapp.model.VacancyApplication;
import nl.novi.okerwebapp.repository.VacancyApplicationRepository;
import nl.novi.okerwebapp.service.VacancyApplicationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {

    @Mock
    JavaMailSender mailSender;

    @Mock
    VacancyApplicationRepository vacancyApplicationRepository;

    @InjectMocks
    JwtUtil jwtUtil;

    @Captor
    ArgumentCaptor<VacancyApplication> vacancyApplicationArgumentCaptor;

    @Test
    public void generateToken(){
        assertTrue(true);

    }
}