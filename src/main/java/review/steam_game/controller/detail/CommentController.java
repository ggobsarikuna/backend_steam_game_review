package review.steam_game.controller.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import review.steam_game.config.security.UserDetailsImpl;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.service.detail.CommentService;

@RestController
@RequestMapping("/detail")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 작성
    @PostMapping("comment/{postId}")
    public String createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto){
        return commentService.createComment(commentRequestDto);
    }

    //댓글 수정
    @PatchMapping("/comment/{postId}/{commentId}")
    public String updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails.getUsername().equals(commentRequestDto.getUserid()) || userDetails.getAuthorities().equals("ROLE_ADMIN")){
            return commentService.updateComment(commentId, commentRequestDto);
        }
        return "수정 권한이 없습니다!";
    }

    //댓글 삭제
    @DeleteMapping("/comment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if (userDetails.getUsername().equals(commentRequestDto.getUserid()) || userDetails.getAuthorities().equals("ROLE_ADMIN")){
            return commentService.deleteComment(commentId);
        }
        return "삭제 권한이 없습니다!";
    }

}
