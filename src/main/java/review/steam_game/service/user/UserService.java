package review.steam_game.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import review.steam_game.config.exception.CheckApiException;
import review.steam_game.config.exception.ErrorCode;
import review.steam_game.config.jwt.JwtUtil;
import review.steam_game.dto.user.UserIdRequestDto;
import review.steam_game.dto.user.UserLoginDto;
import review.steam_game.dto.user.UserSignUpDto;
import review.steam_game.entity.user.User;
import review.steam_game.entity.user.UserRoleEnum;
import review.steam_game.repository.user.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(UserSignUpDto userSignUpDto) {
        String userid = userSignUpDto.getUserid();
        String password = passwordEncoder.encode(userSignUpDto.getPassword());
        String email = userSignUpDto.getEmail();
        UserRoleEnum role = UserRoleEnum.USER;

        Optional<User> firstDuplicate = userRepository.findByUserid(userSignUpDto.getUserid());
        if (firstDuplicate.isPresent()) {
            throw new CheckApiException(ErrorCode.EXISTS_USER);
        }


        User user = new User(userid, password, email, role);
        userRepository.save(user);
    }

    @Transactional
    public void checkUserId(UserIdRequestDto userIdRequestDto) {
        Optional<User> user = userRepository.findByUserid(userIdRequestDto.getUserid());
        log.info(userIdRequestDto.getUserid());
        log.info(user.toString());
        if(user.isPresent()){
            throw new CheckApiException(ErrorCode.EXISTS_USER);
        }
    }

    @Transactional(readOnly = true)
    public void login(UserLoginDto userLoginDto, HttpServletResponse response) {
        String userid = userLoginDto.getUserid();
        String password = userLoginDto.getPassword();

        User user = userRepository.findByUserid(userid).orElseThrow(() -> new CheckApiException(ErrorCode.NOT_EXISTS_USER));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CheckApiException(ErrorCode.NOT_EQUALS_PASSWORD);
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUserid(), user.getRole()));
    }
}
