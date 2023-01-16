package review.steam_game.entity.game;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity(name = "APP_ID")
public class App_id {

    @Id
    @Column(name="appid")
    private int appid;

    @Column(name="name")
    private String name;

    @Column(name="views")
    private int views;
}
