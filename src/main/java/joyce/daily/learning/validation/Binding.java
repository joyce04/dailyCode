package joyce.daily.learning.validation;

import javax.validation.GroupSequence;

public class Binding {

    /**
     * fields that must have values not blank
     */
    public interface Required {
    }

    /**
     * fields that must have valid values such as pattern or enum
     */
    public interface Invalid {
    }

    @GroupSequence(value = {Required.class, Invalid.class})
    public interface Create {
    }
}
