package review.steam_game.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    public Post(PostRequestDto postRequestDto, Image image) {
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
        this.image = image;
    }

    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
    }
}
