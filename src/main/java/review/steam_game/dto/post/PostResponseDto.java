package review.steam_game.dto.post;

import lombok.Getter;
import review.steam_game.entity.Post.Post;

@Getter
public class PostResponseDto {
    private String title;
    private String comment;
    private String imageUrl;
    private Long postId;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.comment = post.getComment();
        this.imageUrl = post.getImage().getImageUrl();
        this.postId = post.getId();
    }
}
