package joyce.daily.learning.fieldExtraction;

import org.junit.Test;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Map;

import static joyce.daily.learning.fieldExtraction.Division.HIGH_SCHOOL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.data.MapEntry.entry;

public class UpdateGeneratorTest {
    private UpdateGenerator<Team> updateGenerator = new UpdateGenerator<>();

    @Test
    public void returnUpdate_withFieldsToUpdate() throws Exception {
        Team team = Team.builder()
                .name("team name")
                .division(HIGH_SCHOOL)
                .headCoach(Coach.builder().name("coach").build()).build();

        Update update = updateGenerator.set(team);
        Map<String, Object> updateMap = (Map<String, Object>) update.getUpdateObject().get("$set");

        assertThat(updateMap).containsOnlyKeys("name"
                , "division"
                , "lastModifiedTimestamp"
                , "headCoach.name"
                , "headCoach.age"
                , "headCoach.startDate");

        assertThat(updateMap).contains(
                entry("name", "team name")
                , entry("division", "HIGH_SCHOOL")
                , entry("headCoach.name", "coach"));

    }
}