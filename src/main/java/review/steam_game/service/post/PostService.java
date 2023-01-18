package review.steam_game.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.steam_game.config.exception.CheckApiException;
import review.steam_game.config.exception.ErrorCode;
import review.steam_game.dto.post.PostCommentResponseDto;
import review.steam_game.entity.Post;
import review.steam_game.repository.post.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public PostCommentResponseDto getDetail(Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new CheckApiException(ErrorCode.NOT_EXISTS_POSTS));
        PostCommentResponseDto detail = new PostCommentResponseDto(post);

        return detail;
    }
}
