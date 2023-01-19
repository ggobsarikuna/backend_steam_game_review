package review.steam_game.controller.detail;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import review.steam_game.config.security.UserDetailsImpl;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.dto.detail.CommentResponseDto;
import review.steam_game.entity.Comment;
import review.steam_game.service.detail.CommentService;

import java.util.List;
@ApiOperation("게시글 댓글 작성 수정 삭제")
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@RestController
@RequestMapping("/detail")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //댓글 전체 조회
    @GetMapping("/comment/{postId}")
    public List<CommentRequestDto> getComment(@PathVariable Long postId) {
        return commentService.getComment(postId);
    }

//    @GetMapping("/comment")
//    public List<CommentRequestDto> testGetComment(){
//        return commentService.testGetComment();
//    }

    //댓글 작성
    @PostMapping("/comment/{postId}")
    public CommentResponseDto createComment(@PathVariable Long postId,
                                            @RequestBody CommentRequestDto commentRequestDto,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return commentService.createComment(postId, commentRequestDto, userDetails.getUser());
    }

    //댓글 작성 테스트
//    @PostMapping("/comment/{postId}")
//    public List<Comment> testCreateComment(@PathVariable String postId, @RequestBody CommentRequestDto commentRequestDto) {
//
//        List<String> comments = commentRequestDto.getComments();
//
//        return commentService.testCreateComment(comments);
//    }

    //댓글 수정
    @PatchMapping("/comment/{postId}/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @RequestBody CommentRequestDto commentRequestDto,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        if (userDetails.getUsername().equals(commentRequestDto.getUserid()) || userDetails.getAuthorities().equals("ROLE_ADMIN")) {
//            return commentService.updateComment(commentId, commentRequestDto);
//        }
        return commentService.updateComment(postId, commentId, commentRequestDto, userDetails.getUser());
    }

    //댓글 삭제
    @DeleteMapping("/comment/{postId}/{commentId}")
    public String deleteComment(@PathVariable Long postId,
                                @PathVariable Long commentId,
                                @AuthenticationPrincipal UserDetailsImpl userDetails) {
        commentService.deleteComment(postId, commentId, userDetails.getUser());
//        if (userDetails.getUsername().equals(commentRequestDto.getUserid()) || userDetails.getAuthorities().equals("ROLE_ADMIN")) {
//            return commentService.deleteComment(commentId);
//        }
        return "삭제 완료!";
    }

}