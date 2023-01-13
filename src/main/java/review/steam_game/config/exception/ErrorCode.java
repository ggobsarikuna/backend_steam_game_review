package review.steam_game.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //ex NOT_VALIDATE_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰이 유효하지 않습니다."),
    NOT_VALIDATE_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰이 유효하지 않습니다."),
    NOT_VALIDATE_ARGUMENT(HttpStatus.BAD_REQUEST, "400", "username,password 규칙에 위배됩니다.");

    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
