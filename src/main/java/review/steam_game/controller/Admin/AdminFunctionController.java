package review.steam_game.controller.Admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.user.UserRoleEnum;
import review.steam_game.service.admin.AdminFunctionService;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@Slf4j
//ADMIN계정만 이용가능
@Secured({UserRoleEnum.Authority.ADMIN})
public class AdminFunctionController {
    private final AdminFunctionService adminFunctionService;

    //post 작성
    @PostMapping(value = "/api/post", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String createPost(@RequestPart(value = "file")MultipartFile file,
                             @RequestPart PostRequestDto postRequestDto) throws IOException {
        log.info(file.getOriginalFilename());
        return adminFunctionService.createPost(file, postRequestDto);
    }

    //post수정
    @PutMapping(value = "/api/post/{postId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public String updatePost(@PathVariable Long postId,
                             @RequestPart(value = "file") MultipartFile file,
                             @RequestPart PostRequestDto postRequestDto) throws IOException {
        log.info(postId.toString());
        return adminFunctionService.updatePost(postId, file, postRequestDto);
    }

    //post삭제
    @DeleteMapping("/api/post/{postId}")
    public String deletePost(@PathVariable Long postId){
        return adminFunctionService.deletePost(postId);
    }
}
