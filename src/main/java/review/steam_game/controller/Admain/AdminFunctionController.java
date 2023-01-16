package review.steam_game.controller.Admain;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.Post;
import review.steam_game.entity.UserRoleEnum;
import review.steam_game.service.admin.AdminFunctionService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Secured(UserRoleEnum.Authority.ADMIN)
public class AdminFunctionController {
    private final AdminFunctionService adminFunctionService;

    @PostMapping("/api/post")
    @ResponseBody
    public Post createPost(@RequestBody PostRequestDto postRequestDto){
        return adminFunctionService.createPost(postRequestDto);
    }

    @PutMapping("/api/post/{postId}")
    @ResponseBody
    public Post updatePost(@RequestBody PostRequestDto postRequestDto,
                           @PathVariable Long postId){
        return adminFunctionService.updatePost(postRequestDto, postId);
    }

    @DeleteMapping("/api/post/{postId}")
    public String deletePost(@PathVariable Long postId){
        return adminFunctionService.deletePost(postId);
    }
}
