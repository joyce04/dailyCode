package joyce.daily.learning.fieldExtraction;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coach {
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private Integer age;
    @Getter
    @Setter
    private Instant startDate;
}
