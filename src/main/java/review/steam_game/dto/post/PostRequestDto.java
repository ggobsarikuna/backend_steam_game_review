package review.steam_game.dto.post;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostRequestDto {
    private String title;
    private String comment;
    private MultipartFile file;
}
