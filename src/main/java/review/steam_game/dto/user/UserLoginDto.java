package review.steam_game.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDto {
    private String userid;
    private String password;
}
