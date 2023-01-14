package review.steam_game.dto.user;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class UserSignUpDto {

    //@NotBlank
    //@Pattern(regexp = "^[a-z0-9]{4,8}$", message = "아이디는 알파벳 소문자, 숫자, 4-10자 이하로 구성됩니다.")
    private String userid;

    //@NotBlank
    //@Pattern(regexp = "^[A-Za-z\\d$@$!%*?&]{8,15}$", message = "비밀번호는 알파벳, 숫자, 특수문자 8-15자 이하로 구성됩니다.")
    private String password;
    private String email;
}
