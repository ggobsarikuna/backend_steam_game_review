package review.steam_game.dto.main;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GameInfoDto {

    private int gameid;

    private String gamename;

    private String image;

    private String genre;

    private int views;
}
