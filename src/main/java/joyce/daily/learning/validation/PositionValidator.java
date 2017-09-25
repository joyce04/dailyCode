package joyce.daily.learning.validation;

public @interface PositionValidator {
    Class<?> groups();

    Class<? extends Enum> enumClass();
}
