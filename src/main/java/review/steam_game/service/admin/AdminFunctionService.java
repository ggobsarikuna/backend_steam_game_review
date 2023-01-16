package review.steam_game.service.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import review.steam_game.dto.post.PostRequestDto;
import review.steam_game.entity.Post;
import review.steam_game.repository.post.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminFunctionService {
    private final PostRepository postRepository;
    public Post createPost(PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto);
        postRepository.save(post);
        return post;
    }

    public Post updatePost(PostRequestDto postRequestDto, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("일치하는 아이디가 없습니다.")
        );
        post.update(postRequestDto);
        return post;
    }

    public String deletePost(Long postId) {
        postRepository.deleteById(postId);
        return "삭제가 완료되었습니다.";
    }
}
