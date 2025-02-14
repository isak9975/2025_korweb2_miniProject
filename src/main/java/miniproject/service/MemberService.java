package miniproject.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import miniproject.model.dto.BoardDto;
import miniproject.model.dto.MemberDto;
import miniproject.model.dto.ReplyDto;
import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.MemberEntity;
import miniproject.model.entity.ReplyEntity;
import miniproject.model.repository.BoardRepository;
import miniproject.model.repository.MemberRepository;
import miniproject.model.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class MemberService {

    @Autowired private MemberRepository memberRepository;
    @Autowired private BoardRepository boardRepository;
    @Autowired private ReplyRepository replyRepository;
    @Autowired private HttpServletRequest request; // 톰캣 서버 요청 객체

    // == 세션 함수 == //

    // [1] 세션 객체 내 정보 추가(setSession) : 로그인 + 로그인한 회원 세션에 저장
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


    // [1] 회원가입 !
    @Transactional
    public boolean signup(MemberDto memberDto) {
        MemberEntity memberEntity = memberDto.toEntity(); // 엔티티 전환
        MemberEntity saveEntity = memberRepository.save(memberEntity); // 엔티티 save 이후 반환
        // 반환된 엔티티의 Mno가 0보다 클 시 true 반환 , 아닐 시 false 반환
        if(saveEntity.getMno() > 0) {return true;} else {return false;}
    } // f ed

    // [2] 로그인 !
    @Transactional
    public boolean login(MemberDto memberDto) {
        // memberDto 에서 mid와 mpwd를 뽑아내 memberRepository 로그인 메서드 호출
        boolean result = memberRepository.existsByMidAndMpwd(memberDto.getMid(),memberDto.getMpwd());

        if (result == true) {
            System.out.println("로그인 성공");
            setSession(memberDto.getMid()); // 로그인 성공 시 세션에 아이디 저장
            return true;
        } else {
            System.out.println("로그인 실패");
            return false;
        }
    } // f ed

    // [3] 로그아웃 !
    // 세션 함수 [3] 참고

    // [4] 내 정보 조회 !
    public MemberDto myInfo() {
        String mid = getSession(); // 현재 로그인된 회원 아이디 조회
        if (mid != null) {
            // 조회된 회원 아이디로 회원 정보 엔티티 조회
            MemberEntity memberEntity = memberRepository.findByMid(mid);
            MemberDto memberDto = memberEntity.toDto(); // Dto 전환
            return memberDto; // 회원정보 Dto 반환
        }
        return null;
    } // f ed

    // [5] 내 게시물 조회 !
    public List<BoardDto> myBoard() {
        String mid = getSession(); // 현재 로그인된 회원 아이디 조회
        if (mid != null) {
            // 조회된 회원 아이디로 회원 정보 엔티티 조회
            MemberEntity memberEntity = memberRepository.findByMid(mid);

            // 내가 작성한 게시물만을 저장할 리스트 생성
            List<BoardDto> myBoard = new ArrayList<>();

            // 모든 게시물 엔티티 가져오기
            List<BoardEntity> boardEntityList = boardRepository.findAll();

            for (int i = 0; i < boardEntityList.size(); i++) {
                // i번째 게시물 꺼내기
                BoardEntity boardEntity = boardEntityList.get(i);

                // 만약 i번째 게시물 작성자의 회원번호가 현재 로그인 중인 회원의 회원번호와 같다면...
                if (boardEntity.getMemberEntity().getMno() == memberEntity.getMno() ) {
                    // i번째 게시물 DTO 변환
                    BoardDto boardDto = boardEntity.toDto();
                    // 내 게시물리스트에 저장
                    myBoard.add(boardDto);
                } // if ed
            } // for ed

            // 내 게시물리스트 반환
            return myBoard;
        }
        return null;
    } // f ed

    // [6] 내 댓글 조회 !
    public List<ReplyDto> myReply() {
        String mid = getSession(); // 현재 로그인된 회원 아이디 조회
        if (mid != null) {
            // 조회된 회원 아이디로 회원 정보 엔티티 조회
            MemberEntity memberEntity = memberRepository.findByMid(mid);

            // 내가 작성한 게시물만을 저장할 리스트 생성
            List<ReplyDto> myReply = new ArrayList<>();

            // 모든 게시물 엔티티 가져오기
            List<ReplyEntity> replyEntityList = replyRepository.findAll();

            for (int i = 0; i < replyEntityList.size(); i++) {
                ReplyEntity replyEntity = replyEntityList.get(i);
                if (replyEntity.getMemberEntity().getMno() == memberEntity.getMno() ) {
                    ReplyDto replyDto = replyEntity.toDto();
                    myReply.add(replyDto);
                } // if ed
            } // for ed
            return myReply;
        }
        return null;
    } // f ed

    // [7] 내 정보 수정 !
    @Transactional
    public boolean myUpdate( MemberDto memberDto ){
        String mid = getSession();
        if( mid != null ){
            MemberEntity memberEntity = memberRepository.findByMid( mid );
            memberEntity.setMpwd( memberDto.getMpwd() );
            memberEntity.setMname( memberDto.getMname() );
            memberEntity.setMphone( memberDto.getMphone() );
            return true;
        }
        return false;
    } // f end

    // [8] 회원 탈퇴 !
    public boolean myDelete() {
        String mid = getSession(); // 현재 로그인 회원 아이디 조회
        // 조회된 회원 아이디로 회원 정보 엔티티 조회
        MemberEntity memberEntity = memberRepository.findByMid(mid);

        // MemberEntity 를 FK 로 참조 중인 ReplyEntity 의 관계를 끊음
        List<ReplyEntity> replyEntities = replyRepository.findByMemberEntity(memberEntity);
        for (ReplyEntity relatedEntity : replyEntities) {
            relatedEntity.setMemberEntity(null);
        }

        // MemberEntity 를 FK 로 참조 중인 boardEntity 의 관계를 끊음
        List<BoardEntity> boardEntities = boardRepository.findByMemberEntity(memberEntity);
        for (BoardEntity relatedEntity : boardEntities) {
            relatedEntity.setMemberEntity(null);
        }

        memberRepository.delete(memberEntity); // 엔티티 삭제 = 탈퇴
        deleteSession(); // 로그아웃
        return true; // 반환
    } // f ed

    // [9] 아이디 찾기
    public String findId(String mname,String mphone) {
        MemberEntity memberEntity = memberRepository.findByMnameAndMphone(mname,mphone);
        if (memberEntity != null) {
            MemberDto memberDto = memberEntity.toDto();
            return memberDto.getMid();
        }
        return null;
    } // f ed

    // [10] 비밀번호 찾기
    public String findPw(String mid,String mphone) {
        MemberEntity memberEntity = memberRepository.findByMidAndMphone(mid,mphone);
        if (memberEntity != null) {
            MemberDto memberDto = memberEntity.toDto();
            return memberDto.getMpwd();
        }
        return null;
    } // f ed

}
