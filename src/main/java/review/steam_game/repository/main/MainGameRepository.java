package review.steam_game.repository.main;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import review.steam_game.entity.game.App_id;
import review.steam_game.entity.game.Game;

import java.util.List;
import java.util.Optional;

public interface MainGameRepository extends JpaRepository<Game, Long> {

    @Query(nativeQuery = true, value = "SELECT A.game_id, A.gamename, A.image, A.genre, A.views FROM GAME A ORDER BY views DESC LIMIT 5")
    List<Optional<Game>> findByViewsDesc();

    List<Optional<Game>> findAllBy();

    List<Optional<Game>> findByGameid(int gameid);
}
