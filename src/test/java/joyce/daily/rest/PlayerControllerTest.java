package joyce.daily.rest;

import joyce.daily.model.Player;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PlayerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void validateRequiredFieldsWithinRequestBody() throws Exception {
        Player player = Player.builder().build();
        ResponseEntity<List> response = restTemplate.postForEntity("/players", player, List.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(response.getBody().size(), 3);

        assertThat(response.getBody()).containsAll(generateRequiredErrors());
    }

    private List<Map<String, String>> generateRequiredErrors() {
        return Stream.of("age", "name", "position")
                .map(str -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("code", "NotBlank");
                    map.put("field", str);
                    return map;
                }).collect(Collectors.toList());
    }
}