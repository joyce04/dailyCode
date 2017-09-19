package joyce.daily.learning.patch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
public class Player {

    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer age;
    @Getter
    @Setter
    private String position;
    @Getter
    @Setter
    private Integer number;
}