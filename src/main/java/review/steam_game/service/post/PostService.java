package review.steam_game.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import review.steam_game.dto.post.PostResponseDto;
import review.steam_game.entity.Post.Post;
import review.steam_game.repository.post.PostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public List<PostResponseDto> showPost() {
        List<Post> posts = postRepository.findAll();
        List<PostResponseDto> postResponseDtoList = new ArrayList<>();
        for(Post post: posts){
            PostResponseDto postResponseDto = new PostResponseDto(post);
            postResponseDtoList.add(postResponseDto);
        }
        return postResponseDtoList;
    }
}
