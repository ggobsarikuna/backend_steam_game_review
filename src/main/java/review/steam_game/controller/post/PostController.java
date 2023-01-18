package review.steam_game.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import review.steam_game.dto.post.PostResponseDto;
import review.steam_game.service.post.PostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @GetMapping("/api/post")
    public List<PostResponseDto> showPost() {
        return postService.showPost();
    }
}
