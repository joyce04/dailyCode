package joyce.daily;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FunctionalTest {

    private static final String SERVER_URL = "http://localhost";
//    private static final String BASE_PATH = "/api/v1";
    private static final String BASE_PATH = "";

    @LocalServerPort
    private int port;

    @Autowired
    protected ObjectMapper objectMapper;

    @Before
    public void initializeObjectMapperToCompareJsonWithoutComments() {
        //해당 설정으로 Json 비교 시, 주석 처리된 부분을 제외하고 비교
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    @Before
    public void initializeRestAssure() {
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = this.port;
        RestAssured.basePath = BASE_PATH;

        // RestAssured로 통신하는 모든 api의 request, response log를 출력하는 설정
        RestAssured.filters(new ResponseLoggingFilter(), new RequestLoggingFilter());

        // RestAssured로 통신하는 api가 실패했을 경우에만 log를 출력하는 설정
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}