package miniproject.controller;

import miniproject.model.dto.BoardDto;
import miniproject.model.dto.PageDto;
import miniproject.model.dto.ReplyDto;
import miniproject.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BoardController {

    @Autowired private BoardService boardService;

    //1. 게시물 작성
    @PostMapping("/board/write.do")
    public boolean write(@RequestBody BoardDto boardDto){
        return  boardService.write(boardDto);
    }

    //2. 게시물 전체 조회
    @GetMapping("/board/findall.do")
    public PageDto findAll(@RequestParam int cno, @RequestParam int page){
        return boardService.findAll(cno,page);
    }

    //3. 게시물 개별 조회
    @GetMapping("/board/find.do")
    public BoardDto find(@RequestParam int bno){
        return boardService.find(bno);
    }

    //4. 게시물 개별 수정
    @PutMapping("/board/update.do")
    public boolean update(@RequestBody BoardDto boardDto){
        return boardService.update(boardDto);
    }

    //5. 게시물 개별 삭제
    @DeleteMapping("/board/delete.do")
    public boolean delete(@RequestParam int bno){
        return boardService.delete(bno);
    }

//===================댓글====================================================================
    //6. 댓글 작성
    @PostMapping("/reply/write.do")
    public boolean replyWrite(@RequestBody ReplyDto replyDto){return boardService.replyWrite(replyDto);}

    //7. 특정 게시물의 댓글 전체 조회
    @GetMapping("/reply/findall.do")
    public List<ReplyDto> replyFindAll(@RequestParam int bno){return boardService.replyFindAll(bno);}

}
