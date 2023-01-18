package review.steam_game.dto.post;

import lombok.Getter;
import review.steam_game.entity.Post.AdminPost;

@Getter
public class AdminPostResponseDto {
    private String title;
    private String comment;
    private String imageUrl;
    private Long postId;

    public AdminPostResponseDto(AdminPost adminPost) {
        this.title = adminPost.getTitle();
        this.comment = adminPost.getComment();
        this.imageUrl = adminPost.getImage().getImageUrl();
        this.postId = adminPost.getId();
    }
}
