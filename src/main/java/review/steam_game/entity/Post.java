package review.steam_game.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.PostRequestDto;

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

    @Column(nullable = false)
    private String image;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto, String imageUrl) {
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
        this.image = imageUrl;
    }

    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
        this.comment = postRequestDto.getComment();
    }
}
