package joyce.daily.model;

import joyce.daily.validation.Binding;
import joyce.daily.validation.PositionValidator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
public class Player {

    @Getter
    @Setter
    @NotBlank(groups = Binding.Required.class)
    private String name;

    @Getter
    @Setter
    @NotNull(groups = Binding.Required.class)
    private Integer age;

    @Getter
    @Setter
    @NotNull(groups = Binding.Required.class)
    @PositionValidator(groups = Binding.Invalid.class, enumClass = Position.class)
    private Position position;

    @Getter
    @Setter
    private Integer number;
}