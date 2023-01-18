package review.steam_game.controller.Admain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import review.steam_game.config.security.UserDetailsImpl;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.Post;
import review.steam_game.entity.user.UserRoleEnum;
import review.steam_game.service.admin.AdminFunctionService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
//ADMIN계정만 이용가능
@Secured({UserRoleEnum.Authority.ADMIN})
public class AdminFunctionController {
    private final AdminFunctionService adminFunctionService;

    //post 작성
    @PostMapping("/api/post")
    public String createPost(@RequestBody PostRequestDto postRequestDto) throws IOException{
        return adminFunctionService.createPost(postRequestDto);
    }

    @PutMapping("/api/post/{postId}")
    public Post updatePost(@RequestBody PostRequestDto postRequestDto,
                           @PathVariable Long postId){
        return adminFunctionService.updatePost(postRequestDto, postId);
    }

    @DeleteMapping("/api/post/{postId}")
    public String deletePost(@PathVariable Long postId){
        return adminFunctionService.deletePost(postId);
    }
}
