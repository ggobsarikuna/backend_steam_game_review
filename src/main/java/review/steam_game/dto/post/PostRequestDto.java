package review.steam_game.dto.post;

import lombok.Getter;
import review.steam_game.entity.Post.Genre;

@Getter
public class PostRequestDto {
    private String title;
    private String explanation;
    private String genre;
}
