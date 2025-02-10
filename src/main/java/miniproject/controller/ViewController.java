package miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("") // 메인 페이지 반환 메서드
    public String index() {return "index.html";}

    @GetMapping("/member/signup") // 회원가입 페이지 반환 메서드
    public String signup() {return "/member/signup.html";}

    @GetMapping("/member/signin") // 로그인 페이지 반환 메서드
    public String login() {return "/member/signin.html";}

    @GetMapping("/member/mypage") // 마이페이지 반환 메서드
    public String myPage() {return "/member/mypage.html";}

    @GetMapping("/board")
    public String board(@RequestParam(value = "type", required = false) String type) {
        if (type != null) {
            switch (type) {
                case "1":
                    return "/board/board_type_1.html";
                case "2":
                    return "/board/board_type_2.html";
                case "3":
                    return "/board/board_type_3.html";
                case "4":
                    return "/board/board_type_4.html";
                case "5":
                    return "/board/board_type_5.html";
                default:
                    // 유효하지 않은 type 값인 경우 기본 페이지를 반환합니다.
                    return "/board/board.html";
            }
        }
        // type 파라미터가 없는 경우 기본 페이지를 반환합니다.
        return "/board/board.html";
    }

}
