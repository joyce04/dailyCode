package joyce.daily.learning.patch;

import joyce.daily.learning.model.Player;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class TeamController {
    //September 19 2017
    //to practice patch rest template test cases

    @PatchMapping("/teams/{teamId}/players")
    @ResponseStatus(HttpStatus.OK)
    public Map updatePlayersToTeam(@PathVariable String teamId, @RequestBody List<Player> players) {
        Optional.ofNullable(teamId).ifPresent(id -> System.out.println("Add players to TEAM:: " + id));
        players.stream().peek(player -> System.out.println(player.toString()));

        Map<String, String> responeMap = new HashMap<>();
        responeMap.put("result", "succesfully update players to team:" + teamId);
        return responeMap;
    }
}