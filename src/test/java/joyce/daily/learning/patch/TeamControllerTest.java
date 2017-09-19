package joyce.daily.learning.patch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TeamControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;
    private RestTemplate patchRestTemplate;

    @Before
    public void setUp() throws Exception {
        patchRestTemplate = restTemplate.getRestTemplate();
        patchRestTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    }

    @Test
    public void PATCH_updatePlayersToTeam_return200() throws Exception {
        List<Player> players = Collections.singletonList(Player.builder().name("player one").number(11).build());
        ResponseEntity<Map> response = restTemplate.exchange("/teams/team_id/players",
                HttpMethod.PATCH,
                new HttpEntity<>(players, new HttpHeaders()),
                Map.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(response.getBody()).containsKey("result");
    }
}