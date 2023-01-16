package review.steam_game.service.detail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.steam_game.dto.detail.CommentRequestDto;
import review.steam_game.entity.Comment;
import review.steam_game.repository.detail.CommentRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;

    //댓글 작성
    public String createComment(CommentRequestDto commentRequestDto) {
        commentRepository.saveAndFlush(new Comment(commentRequestDto));
        return "댓글 등록 성공!";
    }

    //댓글 수정
    public String updateComment(Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow();
        comment.updateComment(commentRequestDto);
        return "댓글 수정 성공!";
    }

    //댓글 삭제
    public String deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
        return "댓글 삭제 성공!";
    }


}
