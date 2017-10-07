package joyce.daily.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Division division;

    @Getter
    @Setter
    private Coach headCoach;
}
