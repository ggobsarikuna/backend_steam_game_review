
package review.steam_game.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import review.steam_game.dto.main.SearchGameDto;
import review.steam_game.entity.game.App_id;
import review.steam_game.service.main.MainService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    @GetMapping("/searchGame")
    @ResponseBody
    public List<Optional<App_id>> searchGame(@RequestBody SearchGameDto searchGameDto) {
        String str1 = searchGameDto.getQuery();
        String str2 = str1.toLowerCase();
        String str3 = str1.toUpperCase();

        return mainService.searchGame(str1,str2,str3);
    }

}
