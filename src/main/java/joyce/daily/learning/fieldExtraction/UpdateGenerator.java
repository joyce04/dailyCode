package joyce.daily.learning.fieldExtraction;

import org.springframework.data.mongodb.core.query.Update;

import java.time.Instant;
import java.util.Arrays;

public class UpdateGenerator<T> {
    //Sep 17 2017
    //getting fields + recursive + MapAssert

    //todo does FieldUtils prover better functions?
    //compile("org.apache.commons:commons-lang3:3.4")

    public Update set(T object) {
        Update update = Update.update("lastModifiedTimestamp", Instant.now());

        Arrays.asList(object.getClass().getDeclaredFields())
                .forEach(field -> {
                    field.setAccessible(true);

                    try {
                        Object value = field.get(object);
                        if (value instanceof String || value instanceof Integer) {
                            update.set(field.getName(), value);
                        } else if (value instanceof Enum) {
                            update.set(field.getName(), value.toString());
                        } else {
                            extractSubFields(update, value, field.getName());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
        return update;
    }

    private void extractSubFields(Update update, Object value, String prefix) {
        if (null == value) { //intentionally inserting null following requirement
            update.set(prefix, null);
            return;
        }

        Arrays.asList(value.getClass().getDeclaredFields())
                .forEach(field -> {
                    try {
                        field.setAccessible(true);
                        Object subValue = field.get(value);
                        if (subValue instanceof String || subValue instanceof Integer) {
                            update.set(prefix + "." + field.getName(), subValue);
                        } else if (subValue instanceof Enum) {
                            update.set(field.getName(), subValue.toString());
                        } else {
                            extractSubFields(update, subValue, prefix + "." + field.getName());
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }
}
