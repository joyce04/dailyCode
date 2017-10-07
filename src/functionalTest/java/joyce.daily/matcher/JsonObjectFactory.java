package joyce.daily.matcher;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class JsonObjectFactory {

    private ObjectMapper objectMapper;

    public JsonObjectFactory() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    public Object generateExpected(Path expected) {
        try {
            Object json = objectMapper.readValue(expected.toFile(), Object.class);

            if (json instanceof Map) {
                return new HashMap<>((Map) json);
            } else if (json instanceof ArrayList) {
                return new ArrayList<>((ArrayList) json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid Json Type...");
    }

    public Object generateActual(String actual) {
        try {
            Object json = objectMapper.readValue(actual, Object.class);

            if (json instanceof Map) {
                return new HashMap<>((Map) json);
            } else if (json instanceof ArrayList) {
                return new ArrayList<>((ArrayList) json);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid Json Type...");
    }
}