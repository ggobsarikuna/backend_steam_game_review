package review.steam_game.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckApiException extends RuntimeException{

    private final ErrorCode errorCode;
}
