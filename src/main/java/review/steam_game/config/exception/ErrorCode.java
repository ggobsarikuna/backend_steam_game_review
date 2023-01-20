package review.steam_game.config.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_VALIDATE_TOKEN(HttpStatus.BAD_REQUEST, "400", "토큰이 유효하지 않습니다."),
    NOT_VALIDATE_USER(HttpStatus.BAD_REQUEST, "400", "작성자만 삭제/수정 할 수 있습니다."),
    ALREADY_EXISTS_USER(HttpStatus.BAD_REQUEST, "400", "중복된 username입니다."),
    NOT_VALIDATE_LOGIN(HttpStatus.BAD_REQUEST, "400", "회원을 찾을 수 없습니다."),
    NOT_VALIDATE_ARGUMENT(HttpStatus.BAD_REQUEST, "400", "username,password 규칙에 위배됩니다."),
    FAIL_CREATE_COMMENT(HttpStatus.BAD_REQUEST, "400", "댓글 쓰기 실패: 해당 게시글이 존재하지 않습니다."),
    NOT_CREATE_COMMENT_USER(HttpStatus.BAD_REQUEST, "400", "댓글 작성자가 아닙니다."),
    NOT_EXISTS_COMMENT(HttpStatus.BAD_REQUEST,"400", "댓글이 존재하지 않습니다."),
    NOT_EXISTS_POSTS(HttpStatus.BAD_REQUEST, "400", "해당 게시글이 없습니다."),
    NOT_EXISTS_POSTS_USER(HttpStatus.BAD_REQUEST, "400", "해당 게시글 작성자가 아닙니다."),
    EXISTS_USER(HttpStatus.BAD_REQUEST, "400", "중복된 사용자가 존재합니다."),
    NOT_EQUALS_PASSWORD(HttpStatus.BAD_REQUEST, "400", "비밀번호가 일치하지 않습니다."),
    NOT_EXISTS_USER(HttpStatus.BAD_REQUEST,"400", "등록된 사용자가 없습니다."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST, "400", "이미지를 추가해야 합니다.");



    private final HttpStatus httpStatus;
    private final String errorCode;
    private final String errorMessage;

    ErrorCode(HttpStatus httpStatus, String errorCode, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
