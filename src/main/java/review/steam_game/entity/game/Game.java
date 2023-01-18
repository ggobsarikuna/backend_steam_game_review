package review.steam_game.entity.game;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "GAME")
public class Game {

    @Id
    @Column(name="game_id")
    private int gameid;

    @Column
    private String gamename;

    @Column
    private String image;

    @Column
    private String genre;

    @Column
    private int views;
}
