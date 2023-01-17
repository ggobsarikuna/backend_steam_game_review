package review.steam_game.controller.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import review.steam_game.dto.ResponseDto;
import review.steam_game.dto.user.UserLoginDto;
import review.steam_game.dto.user.UserSignUpDto;
import review.steam_game.service.user.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:8080", allowedHeaders = "*")
@ApiOperation("User SignUp And Login")
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> signup(@RequestBody UserSignUpDto userSignUpDto) {
        userService.signUp(userSignUpDto);
        String data = "회원가입 성공";
        return ResponseEntity.ok(new ResponseDto(data, 200));
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto, HttpServletResponse response) {
        userService.login(userLoginDto, response);
        String data = "로그인 성공";
        return ResponseEntity.ok(new ResponseDto(data, 200));
    }
}
