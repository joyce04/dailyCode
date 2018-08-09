package joyce.daily;

import io.restassured.http.ContentType;
import joyce.daily.matcher.JsonMatcher;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.nio.file.Path;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class PlayerTest extends FunctionalTest {
    private static final String DIRECTORY = "player/";
    private JsonMatcher jsonMatcher;

    @Before
    public void setUp() throws Exception {
        jsonMatcher = new JsonMatcher();
    }

    @Test
    public void name() throws Exception {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        Path path = Paths.get(classLoader.getResource(DIRECTORY + "post_req_players.json").toURI());
        Path resPath = Paths.get(classLoader.getResource(DIRECTORY + "post_res_players.json").toURI());

        given()
                .contentType(ContentType.JSON)
                .body(path.toFile())
                .when()
                .post("/players")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(jsonMatcher.match(resPath));
    }
}
