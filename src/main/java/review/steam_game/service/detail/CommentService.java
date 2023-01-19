package review.steam_game.service.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.steam_game.config.exception.CheckApiException;
import review.steam_game.config.exception.ErrorCode;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.dto.detail.CommentResponseDto;
import review.steam_game.entity.Comment;
import review.steam_game.entity.Post.Post;
import review.steam_game.entity.user.User;
import review.steam_game.entity.user.UserRoleEnum;
import review.steam_game.repository.detail.CommentRepository;
import review.steam_game.repository.post.PostRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // 전체 댓글 조회
    public List<CommentRequestDto> getComment(Long postId) {
        List<Comment> commentList = commentRepository.findByPost_Id(postId);
        List<CommentRequestDto> commentRequestDtoList = new ArrayList<>();
        for (Comment comment : commentList){
            commentRequestDtoList.add(new CommentRequestDto(comment));
        }
        return commentRequestDtoList;
    }

    //댓글 작성
    public CommentResponseDto createComment(Long id, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CheckApiException(ErrorCode.FAIL_CREATE_COMMENT)
        );

        Comment comment = new Comment(post, commentRequestDto, user);
        commentRepository.save(comment);
//        commentRepository.saveAndFlush(new Comment(commentRequestDto));
        return new CommentResponseDto(comment);
    }

    //댓글 수정
    public CommentResponseDto updateComment(Long postId, Long commentId, CommentRequestDto commentRequestDto, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_POSTS)
        );

        Comment comment;

        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            comment = commentRepository.findByCommentId(commentId).orElseThrow(
                    () -> new CheckApiException(ErrorCode.NOT_EXISTS_COMMENT)
            );
        } else {
            comment = commentRepository.findByIdAndUserId(commentId, user).orElseThrow(
                    () -> new CheckApiException(ErrorCode.NOT_CREATE_COMMENT_USER)
            );
        }

        comment.updateComment(commentRequestDto);

        return new CommentResponseDto(comment);
    }

    //댓글 삭제
    public String deleteComment(Long postId, Long commentId, User user){
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new CheckApiException(ErrorCode.NOT_EXISTS_POSTS)
        );

        Comment comment;

        if(user.getRole().equals(UserRoleEnum.ADMIN)){
            comment = commentRepository.findByCommentId(commentId).orElseThrow(
                    () -> new CheckApiException(ErrorCode.NOT_CREATE_COMMENT_USER)
            );
        } else {
            comment = commentRepository.findByIdAndUserId(commentId, user).orElseThrow(
                    () -> new CheckApiException(ErrorCode.NOT_CREATE_COMMENT_USER)
            );
        }

        commentRepository.deleteById(commentId);

        return "댓글 삭제 성공!";
    }





//    public List<Comment> testCreateComment(List<String> comments, String postId, CommentRequestDto commentRequestDto) {
//        Comment comment = new Comment(commentRequestDto);
//        commentRepository.saveAndFlush(comment);
//        return "댓글 등록 성공!";
//    }


//    public List<CommentRequestDto> testGetComment() {
//        List<Comment> commentList = commentRepository.findById();
//        List<CommentRequestDto> commentRequestDtoList = new ArrayList<>();
//        for (Comment comment : commentList){
//            commentRequestDtoList.add(new CommentRequestDto(comment));
//        }
//        return commentRequestDtoList;
//    }
}
