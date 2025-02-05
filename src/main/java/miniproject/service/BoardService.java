package miniproject.service;


import miniproject.model.dto.BoardDto;
import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.MemberEntity;
import miniproject.model.repository.BoardRepository;
import miniproject.model.repository.CategoryRepository;
import miniproject.model.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired private BoardRepository boardRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private CategoryRepository categoryRepository;

    @Autowired private MemberService memberService;


    //1. 게시물 작성
    public boolean write(BoardDto boardDto){
        //로그인된 회원 객체 불러오기
        //Optional<MemberEntity> optional = memberService.myInfo();



        BoardEntity boardEntity = boardDto.toEntity();

        BoardEntity saveEntity = boardRepository.save(boardEntity);

        if(saveEntity != null){
            return true;
        }
        return  false;
    }

    //2. 게시물 전체 조회
    public List<BoardDto> findAll(){
        //1. 현재 저장되어 있는 게시물 List<Entity>로 불러오기
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        //2. 반환할때 필요한 List<Dto> 저장소 만들어주기.
        List<BoardDto> boardDtoList = new ArrayList<>();

        //3. 엔티티를 돌리며 하나씩 조회해서 Dto로 변환 후 List<Dto>에 넣어줌
        boardEntityList.forEach(boardEntity -> {
            boardDtoList.add( boardEntity.toDto() );
        });

        return boardDtoList;
    }

    //3. 게시물 개별 조회
    public BoardDto find(int bno){
        //option 객체로 불러서 확인
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        //만약 조회된 데이터가 있으면
        if(optional.isPresent()){
            //조회된 데이터를 Dto 로 변환.
            BoardDto boardDto = optional.get().toDto();
            //Dto 를 반환;
            return boardDto;
        }
        System.out.println("[오류]조회된 데이터가 없습니다");
        return null;
    }

    //4. 게시물 개별 수정
    public boolean update(int bno){
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
        return false;
    }

    //5. 게시물 개별 삭제
    public boolean delete(int bno){


        return false;
    }
}
