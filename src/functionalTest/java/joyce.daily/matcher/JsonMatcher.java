package joyce.daily.matcher;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JsonMatcher extends TypeSafeDiagnosingMatcher<String> {

    private final JsonObjectFactory jsonObjectFactory;

    private Object expected;

    public JsonMatcher() {
        this.jsonObjectFactory = new JsonObjectFactory();
    }

    @Override
    protected boolean matchesSafely(String given, Description mismatchDescription) {
        Object actual = jsonObjectFactory.generateActual(given);

        if (expected instanceof Map && actual instanceof Map) {
            return ((Map) actual).entrySet().containsAll(((Map) expected).entrySet());
        } else if (expected instanceof ArrayList && actual instanceof ArrayList) {
            return ((ArrayList) actual).containsAll((Collection<?>) expected);
        } else {
            return false;
        }
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(this.expected);
    }

    public JsonMatcher match(Path resPath) {
        this.expected = jsonObjectFactory.generateExpected(resPath);
        return this;
    }
}