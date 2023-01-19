package review.steam_game.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.steam_game.config.exception.CheckApiException;
import review.steam_game.config.exception.ErrorCode;
import review.steam_game.dto.post.PostCommentResponseDto;
import review.steam_game.dto.post.PostResponseDto;
import review.steam_game.entity.Post.Post;
import review.steam_game.repository.post.PostRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<PostResponseDto> showPost() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for (Post post : posts) {
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }
}
