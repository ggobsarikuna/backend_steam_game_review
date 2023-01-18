package review.steam_game.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import review.steam_game.dto.post.AdminPostRequestDto;
import review.steam_game.entity.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class AdminPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String comment;

    @OneToMany(mappedBy = "adgminPost", cascade = CascadeType.ALL, orphanRemoval=true)
    private List<Comment> commentList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "IMAGE_ID")
    private Image image;

    public AdminPost(AdminPostRequestDto adminPostRequestDto, Image image) {
        this.title = adminPostRequestDto.getTitle();
        this.comment = adminPostRequestDto.getComment();
        this.image = image;
    }

    public void update(AdminPostRequestDto adminPostRequestDto){
        this.title = adminPostRequestDto.getTitle();
        this.comment = adminPostRequestDto.getComment();
    }
}
