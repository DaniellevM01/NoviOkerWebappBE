package controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.net.http.HttpResponse;

class VacancyControllerTests {

    @Test
    void testGetVacancies() {
        //given
        String id = RandomStringUtils.randomAlphabetic(8);
        HttpUriRequest request = new HttpGet("https://localhost:8443/vacancies/" + id);
        //when
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
        //then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.NOT_FOUND));
    }

}
