package miniproject.service;

import miniproject.model.dto.BaordDto;
import miniproject.model.entity.BoardEntity;
import miniproject.model.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    //1. 게시물 작성
    public boolean write(BaordDto baordDto){

        BoardEntity boardEntity = baordDto.toEntity();

        BoardEntity saveEntity = boardRepository.save(boardEntity);

        if(saveEntity != null){
            return true;
        }
        return  false;
    }

    //2. 게시물 전체 조회
    public List<BaordDto> findAll(){
        //1. 현재 저장되어 있는 게시물 List<Entity>로 불러오기
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        //2. 반환할때 필요한 List<Dto> 저장소 만들어주기.
        List<BaordDto> baordDtoList = new ArrayList<>();

        //3. 엔티티를 돌리며 하나씩 조회해서 Dto로 변환 후 List<Dto>에 넣어줌
        boardEntityList.forEach(boardEntity -> {
            baordDtoList.add( boardEntity.toDto() );
        });

        return baordDtoList;
    }

    //3. 게시물 개별 조회
    public BaordDto find(int bno){
        return null;
    }

    //4. 게시물 개별 수정
    public boolean update(int bno){
        return false;
    }

    //5. 게시물 개별 삭제
    public boolean delete(int bno){
        return false;
    }
}
