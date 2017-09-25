package joyce.daily.learning.exceptionHandler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void returnExceptionMap_withCustomMessage_whenCustomExceptionOccurs() throws Exception {
        ResponseEntity<Map> response = restTemplate.exchange(
                "/test/custom-exception/",
                HttpMethod.GET,
                new HttpEntity<>(null, new HttpHeaders()),
                Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThat(response.getBody()).containsOnly(
                entry("code", "CUSTOM"),
                entry("message", "custom exception message")
        );
    }

    @Test
    public void returnExceptionMap_withRuntimeMessage_whenRuntimeExceptionOccurs() throws Exception {
        ResponseEntity<Map> response = restTemplate.exchange(
                "/test/exception",
                HttpMethod.POST,
                new HttpEntity<>(null, new HttpHeaders()),
                Map.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertThat(response.getBody()).containsOnly(
                entry("code", "BASE"),
                entry("message", "run time exception")
        );
    }
}