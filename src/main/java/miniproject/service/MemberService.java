package miniproject.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import miniproject.model.dto.BoardDto;
import miniproject.model.dto.MemberDto;
import miniproject.model.dto.ReplyDto;
import miniproject.model.repository.BoardRepository;
import miniproject.model.repository.MemberRepository;
import miniproject.model.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class MemberService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private BoardRepository boardRepository;
    @Autowired private ReplyRepository replyRepository;
    @Autowired private HttpServletRequest request; // 톰캣 서버 요청 객체

    // == 세션 함수 == //

    // [1] 세션 객체 내 정보 추가(setSession) : 로그인
    public boolean setSession(String mid) {
        HttpSession httpSession = request.getSession(); // 요청 객체를 통한 톰캣 내 세션 객체 반환
        httpSession.setAttribute("loginid",mid); // 세션 객체에 속성(새로운 값 추가)
        return true;
    } // f ed

    // [2] 세션 객체 내 정보 반환(getSession)
    public String getSession() {
        HttpSession httpSession = request.getSession(); // 요청 객체를 통한 톰캣 내 세션 객체 반환
        Object object = httpSession.getAttribute("loginid"); // 세션 객체에 속성명의 값 반환
        if (object != null) { // 세션 정보 검사
            String mid = (String)object; // object 타입을 String 타입으로 바꾸어 반환
            return mid;
        }
        return null;
    }

    // [3] 세션 객체 내 정보 초기화(deleteSession) : 로그아웃
    public boolean deleteSession() {
        HttpSession httpSession = request.getSession(); // 요청 객체를 통한 톰캣 내 세션 객체 반환
        httpSession.removeAttribute("loginid"); // 세션 객체 내 특정 한 속성명 제거
        return true;
    }

    // == 세션 함수 ed == //


    // [1] 회원가입
    public boolean signup(MemberDto memberDto) {
        return false;
    } // f ed

    // [2] 로그인
    public boolean login(MemberDto memberDto) {
        return false;
    } // f ed

    // [3] 로그아웃
    public boolean logout() {
        return false;
    } // f ed

    // [4] 내 정보 조회
    public MemberDto myInfo() {
        return null;
    } // f ed

    // [5] 내 게시물 조회
    public BoardDto myBoard() {
        return null;
    } // f ed

    // [6] 내 댓글 조회
    public ReplyDto myReply() {
        return null;
    } // f ed

    // [7] 내 정보 수정
    public boolean myUpdate(MemberDto memberDto) {
        return false;
    } // f ed

    // [8] 회원 탈퇴
    public boolean myDelete(int mno) {
        return false;
    } // f ed
}
