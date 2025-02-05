package miniproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

    @Autowired private MemberService memberService;

    // [1] 회원가입
    @PostMapping("/member/signup.do")
    public boolean signup(@RequestBody MemberDto memberDto) {
        return memberService.signup(memberDto);
    } // f ed

    // [2] 로그인
    @PostMapping("/member/login.do")
    public boolean login(@RequestBody MemberDto memberDto) {
        return memberService.login(memberDto);
    } // f ed

    // [3] 로그아웃
    @GetMapping("/member/logout.do")
    public boolean logout() {
        return memberService.logout();
    } // f ed

    // [4] 내 정보 조회
    @GetMapping("/member/myinfo.do")
    public MemberDto myInfo() {
        return memberService.myInfo();
    } // f ed

    // [5] 내 게시물 조회
    @GetMapping("/member/myboard.do")
    public BoardDto myboard() {
        return memberService.myBoard();
    } // f ed

    // [6] 내 댓글 조회
    @GetMapping("/member/myreply.do")
    public ReplyDto myreply() {
        return memberService.myReply();
    } // f ed

    // [7] 내 정보 수정
    @PutMapping("/member/myupdate.do")
    public boolean myUpdate(@RequestBody MemberDto memberDto) {
        return memberService.myUpdate(memberDto);
    } // f ed

    // [8] 회원 탈퇴
    @DeleteMapping("/member/mydelete.do")
    public boolean myDelete(@RequestParam int mno) {
        return memberService.myDelete(mno);
    } // f ed

} // class ed
