package review.steam_game.controller.post;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import review.steam_game.dto.post.AdminPostResponseDto;
import review.steam_game.service.post.AdminPostService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminPostController {
    private final AdminPostService adminPostService;
    @GetMapping("/api/post")
    public List<AdminPostResponseDto> showPost() {
        return adminPostService.showPost();
    }
}
