package miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ViewController {

    @GetMapping("/") // 메인 페이지 반환 메서드
    public String index() {
        return "index";
    }

    @GetMapping("/member/signup") // 회원가입 페이지 반환 메서드
    public String signup() {
        return "member/signup";
    }

    @GetMapping("/member/signin") // 로그인 페이지 반환 메서드
    public String login() {
        return "member/signin";
    }

    @GetMapping("/member/mypage") // 마이페이지 반환 메서드
    public String myPage() {
        return "member/mypage";
    }

    @GetMapping("/board") // 게시판 페이지 반환 메서드
    public String board(@RequestParam(value = "type", required = false) String type) {
        return "board/board"; // templates/board/board.html로 매핑됩니다.
    }

    //게시물 개별 조회 페이지를 반환
    @GetMapping("/view")
    public String boardview(){return "/board/view.html";}

    //게시물 개별 작성 페이지를 반환
    @GetMapping("/write")
    public String boardwrite(){return "/board/write.html";}

}
