package review.steam_game.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.PostRequestDto;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String image;

    public Post(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
        this.image = postRequestDto.getImage();
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
        this.image = postRequestDto.getImage();
    }
}
