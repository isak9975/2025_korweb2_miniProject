package miniproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("") // 메인 페이지 반환 메서드
    public String index() {return "index.html";}

    @GetMapping("/member/signup") // 회원가입 페이지 반환 메서드
    public String signup() {return "/member/signup.html";}

    @GetMapping("/member/login") // 로그인 페이지 반환 메서드
    public String login() {return "/member/login.html";}
}
