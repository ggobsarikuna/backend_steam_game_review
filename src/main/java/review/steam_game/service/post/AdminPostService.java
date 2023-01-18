package review.steam_game.service.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import review.steam_game.dto.post.AdminPostResponseDto;
import review.steam_game.entity.Post.AdminPost;
import review.steam_game.repository.post.AdminPostRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPostService {
    private final AdminPostRepository adminPostRepository;
    public List<AdminPostResponseDto> showPost() {
        List<AdminPost> adminPosts = adminPostRepository.findAll();
        List<AdminPostResponseDto> adminPostResponseDtoList = new ArrayList<>();
        for(AdminPost adminPost : adminPosts){
            AdminPostResponseDto adminPostResponseDto = new AdminPostResponseDto(adminPost);
            adminPostResponseDtoList.add(adminPostResponseDto);
        }
        return adminPostResponseDtoList;
    }
}
