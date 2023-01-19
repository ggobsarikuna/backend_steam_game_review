package review.steam_game.dto.post;

import lombok.Getter;
import review.steam_game.entity.Post.Post;

@Getter
public class PostResponseDto {
    private final String title;
    private final String explanation;
    private final String imageUrl;
    private final Long postId;
    private final String genre;

    public PostResponseDto(Post post) {
        this.title = post.getTitle();
        this.explanation = post.getExplanation();
        this.imageUrl = post.getImage().getImageUrl();
        this.postId = post.getId();
        this.genre = post.getGenre().toString();
    }
}
