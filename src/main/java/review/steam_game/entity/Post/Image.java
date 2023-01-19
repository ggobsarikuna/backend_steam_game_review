package review.steam_game.entity.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String imageUrl;
    @Column(nullable = false)
    private String imageName;

    public Image(String saveFileName, String imageUrl) {
        this.imageUrl = imageUrl;
        this.imageName = saveFileName;
    }

    public void update(String saveFileName, String imageUrl) {
        this.imageName = saveFileName;
        this.imageUrl = imageUrl;
    }
}
