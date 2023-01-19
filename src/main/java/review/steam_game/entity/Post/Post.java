package review.steam_game.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.BaseTimeEntity;
import review.steam_game.entity.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String explanation;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private Genre genre;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    public Post(PostRequestDto postRequestDto, Image image) {
        this.title = postRequestDto.getTitle();
        this.explanation = postRequestDto.getExplanation();
        this.genre = Enum.valueOf(Genre.class ,postRequestDto.getGenre());
        this.image = image;

    }

    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.explanation = postRequestDto.getExplanation();
    }
}
