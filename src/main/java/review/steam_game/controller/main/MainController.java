
package review.steam_game.controller.main;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import review.steam_game.dto.main.GameInfoDto;
import review.steam_game.dto.main.SearchGameDto;
import review.steam_game.entity.game.App_id;
import review.steam_game.entity.game.Game;
import review.steam_game.service.main.MainService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final MainService mainService;

    @GetMapping("/searchGame/{query}")
    @ResponseBody
    public List<Optional<App_id>> searchGame(@PathVariable String query) {
        String str1 = query;
        String str2 = str1.toLowerCase();
        String str3 = str1.toUpperCase();

        return mainService.searchGame(str1,str2,str3);
    }

    @GetMapping("/mainSlider")
    @ResponseBody
    public List<Optional<Game>> mainSlider() {
        return mainService.mainSlider();
    }

    @GetMapping("/gameList")
    @ResponseBody
    public List<Optional<Game>> gameList() {
        return mainService.gameList();
    }

    @GetMapping("/gameDetail/{gameid}")
    @ResponseBody
    public List<Optional<Game>> gameDetail(@PathVariable int gameid) {
        return mainService.gameDetail(gameid);
    }

}
