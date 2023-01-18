package review.steam_game.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Post extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="post_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String genre;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Comment> commentList = new ArrayList<>();

    public Post(PostRequestDto postRequestDto, String imageUrl) {
        this.title = postRequestDto.getTitle();
        this.imageUrl = imageUrl;
    }


    public void update(PostRequestDto postRequestDto) {
        this.title = postRequestDto.getTitle();
    }
}
