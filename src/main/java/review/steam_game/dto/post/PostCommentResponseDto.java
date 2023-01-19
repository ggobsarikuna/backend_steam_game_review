package review.steam_game.dto.post;

import lombok.Getter;
import lombok.Setter;
import review.steam_game.entity.Post.Genre;
import review.steam_game.entity.Post.Post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class PostCommentResponseDto {

    private Long id;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
    private String title;
    private String genre;
    private String imageUrl;

    private List<CommentResponseDto> comments;

    public PostCommentResponseDto(Post post) {
        this.id = post.getId();
        this.createAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
        this.title = post.getTitle();
        this.imageUrl = post.getImage().getImageUrl();
        this.genre = post.getGenre().toString();
        this.comments = post.getCommentList().stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }
}
