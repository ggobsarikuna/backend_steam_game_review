package review.steam_game.service.main;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import review.steam_game.dto.main.GameInfoDto;
import review.steam_game.entity.game.App_id;
import review.steam_game.entity.game.Game;
import review.steam_game.repository.main.MainGameRepository;
import review.steam_game.repository.main.MainRepository;
import review.steam_game.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainService {

    private final MainRepository mainRepository;
    private final MainGameRepository mainGameRepository;

    public List<Optional<App_id>> searchGame(String str1, String str2, String str3) {
        List<Optional<App_id>> appid;
        if (str1.equals("")) {
            //return 등록된 리뷰가 없으니 첫번째 리뷰를 등록해주세요.
            appid = new ArrayList<>();
        } else if (str1.equals(str3)) {
            appid = mainRepository.findByNameStr2(str1,str2);
        } else if (str1.equals(str2)){
            appid = mainRepository.findByNameStr3(str1,str3);
        } else if (str1.replace(" ", "").equals("")) {
            appid = new ArrayList<>();
        } else {
            appid = mainRepository.findByName(str1,str2,str3);
        }
        return appid;
    }

    public List<Optional<Game>> mainSlider() {
        List<Optional<Game>> bannerList;
        bannerList = mainGameRepository.findByViewsDesc();
        return bannerList;
    }

    public List<Optional<Game>> gameList() {
        List<Optional<Game>> gamelist;
        gamelist =  mainGameRepository.findAllBy();
        return gamelist;
    }

    public List<Optional<Game>> gameDetail(int gameid) {
        List<Optional<Game>> gameInfoList;
        gameInfoList = mainGameRepository.findByGameid(gameid);
        return gameInfoList;
    }

}
