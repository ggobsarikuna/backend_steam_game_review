package review.steam_game.controller.post;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import review.steam_game.dto.post.PostCommentResponseDto;
import review.steam_game.service.post.PostService;

@ApiOperation("게임 정보 상세")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post/{postId}")
    public PostCommentResponseDto getDetail(@PathVariable Long postId) {
        PostCommentResponseDto detail = postService.getDetail(postId);
        return detail;
    }


}