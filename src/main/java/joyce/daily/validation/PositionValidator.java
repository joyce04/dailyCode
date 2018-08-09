package joyce.daily.validation;

public @interface PositionValidator {
    Class<?> groups();

    Class<? extends Enum> enumClass();
}
