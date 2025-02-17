package miniproject.service;


import jakarta.transaction.Transactional;
import miniproject.model.dto.BoardDto;
import miniproject.model.dto.MemberDto;
import miniproject.model.dto.PageDto;
import miniproject.model.dto.ReplyDto;
import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.CategoryEntity;
import miniproject.model.entity.MemberEntity;
import miniproject.model.entity.ReplyEntity;
import miniproject.model.repository.BoardRepository;
import miniproject.model.repository.CategoryRepository;
import miniproject.model.repository.MemberRepository;
import miniproject.model.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private CategoryRepository categoryRepository;
    @Autowired private ReplyRepository replyRepository;

    @Autowired private MemberService memberService;


    ///1. 게시물 작성
    public boolean write(BoardDto boardDto){
        //로그인된 회원 객체 불러오기
        MemberDto loginDto = memberService.myInfo();

        //받은 데이터 엔티티로 교체
        BoardEntity boardEntity = boardDto.toEntity();

        //만약 로그인된 상태가 아니면 글쓰기 종로
            if(loginDto.getMid() == null){
                System.out.println("미로그인 상태");
                return false;
            }

        int loginMno = memberRepository.findByMid(memberService.getSession()).getMno(); //

        //로그인된 회원번호로 회원 엔티티르 호출 및 게시물 엔티티에 대입

        MemberEntity loginEntity = memberRepository.findById(loginMno).get(); //

    boardEntity.setMemberEntity(loginEntity);

        CategoryEntity categoryEntity = categoryRepository.findById(boardDto.getCno()).get();
    boardEntity.setCategoryEntity(categoryEntity);


        BoardEntity saveEntity = boardRepository.save(boardEntity);

        if(saveEntity != null){
            System.out.println("게시판 저장 완료");
            return true;
        }
        return  false;
    }


    ///2. 게시물 전체 조회
    public PageDto findAll(int cno,int page){
        //페이징 처리
        Pageable pageable = PageRequest.of(page-1,8, Sort.by(Sort.Direction.DESC,"bno"));
        //1. 현재 저장되어 있는 게시물 page<Entity>로 불러오기 -> 페이징 처리
        Page<BoardEntity> boardEntityList = boardRepository.findByCategoryEntity_Cno(cno,pageable);
        //2. 반환할때 필요한 List<Dto> 저장소 만들어주기.
        List<BoardDto> boardDtoList = new ArrayList<>();

        //3. 엔티티를 돌리며 하나씩 조회해서 Dto로 변환 후 List<Dto>에 넣어줌

        boardEntityList.forEach(entity -> {
//            if (entity.getCategoryEntity().getCno() == cno) { //페이징 처리에서 cno로 찾아주기에 안함
            boardDtoList.add( entity.toDto() );
//            }
        });
        //return boardDtoList; -->  페이징 처리 때문에 주석 처리함.
        //(1)현재 페이지 번호 = page
        //(2)전체 페이지 번호 - totalPage JPA 에서 지원
            int totalPage = boardEntityList.getTotalPages();
        //(3)전체 조회 된 수 = totalCount JPA의 .getTotalElemnts()
            long totalCount = boardEntityList.getTotalElements();

            int btnSize = 5; //페이지당 표시할 페이징 버튼 수

            int startBtn = ((page-1)/btnSize)*btnSize+1;

            int endBtn = startBtn + (btnSize-1);

            if(endBtn >= totalPage)endBtn=totalPage;

            PageDto pageDto = PageDto.builder()
                    .totalcount(totalCount)
                    .page(page)
                    .totalpage(totalPage)
                    .startbtn(startBtn)
                    .endbtn(endBtn)
                    .data(boardDtoList)
                    .build();
        return pageDto;

    }

    ///3. 게시물 개별 조회
    public BoardDto find(int bno){
        //option 객체로 불러서 확인
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        //만약 조회된 데이터가 있으면
        if(optional.isPresent()){
            //조회된 데이터를 Dto 로 변환.
            BoardDto boardDto = optional.get().toDto();


        // * 현재 게시물의 댓글 리스트 조회
            //1. 모든 댓글 엔티티 조회
            List<ReplyEntity> replyEntityList = replyRepository.findAll();

            //2. 모든 댓글의 엔티티를 Dto 로 변환
            //1) Dto 를 저장할 리스트 만들기
            List<ReplyDto> replyDtoList = new ArrayList<>();

            //2) 댓글 선회하며(entity=>Dto) 게시물에 맞는 댓글 가져오기
            replyEntityList.forEach((reply -> {
                System.out.println(reply);
                if(reply.getBoardEntity().getBno()==bno) {

                    replyDtoList.add(reply.toDto());

                }

            }));

            boardDto.setReplyDto(replyDtoList);

            //Dto 를 반환;
            return boardDto;
        }
        System.out.println("[오류]조회된 데이터가 없습니다");
        return null;
    }

    ///4. 게시물 개별 수정
    public boolean update(BoardDto boardDto){
        //1. 게시물 객체 검사
            //option 객체로 불러서 확인
            Optional<BoardEntity> optional = boardRepository.findById(boardDto.getBno());
            //만약 조회된 데이터가 있으면
            if(optional.isPresent()){
                //조회된 데이터를 Dto 로 변환.
                BoardDto result = optional.get().toDto();
                //Dto 를 반환;
                return false;
            }
            System.out.println("[오류]조회된 데이터가 없습니다");

        //2. 유효성 검사.
            if(memberService.myInfo().getMno() == boardRepository.findById(boardDto.getBno()).get().getMemberEntity().getMno()){
                //3. 업데이트 실행
                    //1) 선택한 게시물 가져오기
                    BoardEntity savedEntity = boardRepository.findById(boardDto.getBno()).get();
                    //2) 작성한 내용 엔티티로 변환
                    BoardEntity boardEntity = boardDto.toEntity();
                    //3) 작성한 내용을 선택한 게시물에 수정
                    savedEntity.setBtitle(boardEntity.getBtitle());
                    savedEntity.setBcontent(boardEntity.getBcontent());

                    BoardEntity result = boardRepository.save(savedEntity);

                    if(result.getBno()>0){
                        System.out.println("게시물 작성 성공");
                        return true;
                    }
                    else{
                        System.out.println("게시물 작성 실패");
                        return false;}
            }
            else{
                System.out.println("작성자만 가능합니다");
                return false;
            }
    }

    //5. 게시물 개별 삭제
    public boolean delete(int bno){
        //1. 게시물 검사
            //option 객체로 불러서 확인
            Optional<BoardEntity> optional = boardRepository.findById(bno);
            //만약 조회된 데이터가 있으면
            if(optional.isPresent()){
                //조회된 데이터를 Dto 로 변환.
                BoardDto boardDto = optional.get().toDto();
                //Dto 를 반환;
                return false;
            }
            System.out.println("[오류]조회된 데이터가 없습니다");

        //2. 유효성 검사.
            if(memberService.myInfo().getMno() == boardRepository.findById(bno).get().getMemberEntity().getMno()){
                boardRepository.deleteById(bno);

                //3. 삭제 실행
                boardRepository.deleteById(bno);
                System.out.println("삭제가 완료 되었습니다.");
                return true;
            }
            else{
                System.out.println("작성자만 가능합니다");
                return false;
            }

        //3. 게시물 삭제 진행
    }

//===================================댓글 서비스 =============================================================================
    //6. 댓글 쓰기
    @Transactional
    public boolean replyWrite(ReplyDto replyDto){
        System.out.println(replyDto);
        //[유효성검사] 1. 현재 로그인된 회원 정보 조회
        MemberDto memberDto = memberService.myInfo();
        //2. 만약에 로그인된 정보가 없다면 종료
        if(memberDto==null){
            System.out.println("로그인이 안되어 있습니다");
            return false;
        }

        //현재 로그인된 회원의 엔티티 조회
        MemberEntity memberEntity = memberRepository.findById(memberDto.getMno()).get();
        //현재 작성할 댓글이 위치한 조회중인 게시물 정보 엔티티 조회

        int bno = replyDto.getBno();
        BoardEntity boardEntity = boardRepository.findById(bno).get();

        //4. 입력 받은 매개변수 Dto => entity로 변환
        ReplyEntity replyEntity = replyDto.toEntity();
        ReplyEntity saveEntity = replyRepository.save(replyEntity);
        saveEntity.setBoardEntity(boardEntity);
        saveEntity.setMemberEntity(memberEntity);

        if(saveEntity.getRno()>0){return true;} // 댓글번호 생성 되었다면 등록 성공.
        return false;

    }



    //7. 특정 게시물의 댓글 전체 조회  -> 게시물 열렸을때 같이 열릴
    public List<ReplyDto> replyFindAll(int bno){

        //1. 모든 댓글 엔티티 조회
        List<ReplyEntity> replyEntityList = replyRepository.findAll();

        //2. 모든 댓글의 엔티티를 Dto 로 변환
            //1) Dto 를 저장할 리스트 만들기
            List<ReplyDto> replyDtoList = new ArrayList<>();

            //2) 댓글 선회하며(entity=>Dto) 게시물에 맞는 댓글 가져오기
            replyEntityList.forEach((reply -> {

                if(reply.getBoardEntity().getBno()==bno) {
                    replyDtoList.add(reply.toDto());
                }

            }));
            return replyDtoList;

//        return null;
    }




}
