package joyce.daily.learning.validation;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum PositionEnum {
    ATTACK("attack"),
    MIDFIELD("midfield"),
    DEFENCE("defence"),
    GOALKEEPER("goalKeeper"),
    NONE("none");

    private static Map<String, PositionEnum> bindingMap = new HashMap<>();

    static {
        bindingMap.put("attack", ATTACK);
        bindingMap.put("midfield", MIDFIELD);
        bindingMap.put("defence", DEFENCE);
        bindingMap.put("goalKeeper", GOALKEEPER);
    }

    private String value;

    PositionEnum(String value) {
        this.value = value;
    }

    @JsonCreator
    public static PositionEnum fromValue(String value) {
        return Optional.ofNullable(bindingMap.get(value))
                .orElse(NONE);
    }
}
