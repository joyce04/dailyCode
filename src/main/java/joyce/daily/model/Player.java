package joyce.daily.model;

import joyce.daily.validation.Binding;
import joyce.daily.validation.PositionValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
public class Player {

    @Getter
    @Setter
    @NotBlank(groups = Binding.Required.class)
    private String name;

    @Getter
    @Setter
    @NotBlank(groups = Binding.Required.class)
    @Pattern(groups = Binding.Invalid.class, regexp = "^[0-9]{2}")
    private Integer age;

    @Getter
    @Setter
    @NotBlank(groups = Binding.Required.class)
    @PositionValidator(groups = Binding.Invalid.class, enumClass = Position.class)
    private String position;

    @Getter
    @Setter
    private Integer number;
}