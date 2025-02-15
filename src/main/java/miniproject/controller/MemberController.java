package miniproject.controller;

import miniproject.model.dto.BoardDto;
import miniproject.model.dto.MemberDto;
import miniproject.model.dto.ReplyDto;
import miniproject.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MemberController {

    @Autowired
    private MemberService memberService;

    // [1] 회원가입
    @PostMapping("/member/signup.do")
    public boolean signup(@RequestBody MemberDto memberDto) {
        return memberService.signup(memberDto);
    } // f ed

    // [2] 로그인
    @PostMapping("/member/signin.do")
    public boolean login(@RequestBody MemberDto memberDto) {
        return memberService.login(memberDto);
    } // f ed

    // [2-1] 로그인된 회원 세션에 IN
    @GetMapping("/member/signin/id.do")
    public String loginId() {
        return memberService.getSession();
    }

    // [3] 로그아웃
    @GetMapping("/member/logout.do")
    public boolean logout() {
        return memberService.deleteSession();
    } // f ed

    // [4] 내 정보 조회
    @GetMapping("/member/mypage.do")
    public MemberDto myInfo() {
        return memberService.myInfo();
    } // f ed

    // [5] 내 게시물 조회
    @GetMapping("/member/myboard.do")
    public List<BoardDto> myboard() {
        return memberService.myBoard();
    } // f ed

    // [6] 내 댓글 조회
    @GetMapping("/member/myreply.do")
    public List<ReplyDto> myreply() {
        return memberService.myReply();
    } // f ed

    // [7] 내 정보 수정
    @PutMapping("/member/update.do")
    public boolean myUpdate(@RequestBody MemberDto memberDto) {
        return memberService.myUpdate(memberDto);
    } // f ed

    // [8] 회원 탈퇴
    @DeleteMapping("/member/delete.do")
    public boolean myDelete(){
        return memberService.myDelete();
    }

    // [9] 내 아이디 찾기
    @GetMapping("/member/findid.do")
    public String findId(@RequestParam String mname , @RequestParam String mphone) {
        return memberService.findId(mname,mphone);
    }

    // [10] 내 비밀번호 찾기
    @GetMapping("/member/findpw.do")
    public String findPw(@RequestParam String mid , @RequestParam String mphone) {
        return memberService.findPw(mid,mphone);
    }

} // class ed
