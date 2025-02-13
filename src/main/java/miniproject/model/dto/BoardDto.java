package miniproject.model.dto;

import lombok.*;
import miniproject.model.entity.BoardEntity;

import java.util.List;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardDto {
    /// 기본적인 boardDto
        //게시물 번호(PK)
        private int bno;
        //게시물 제목
        private String btitle;
        //게시물 내용
        private String bcontent;
        //게시물 조회수
        private int bview;
        //게시물 작성일자
        private String cdate;

    /// 추가 정보를 위한
        //게시물 작성자 번호(FK)
        private int mno;
        //게시물 카테고리 번호(FK)
        private int cno;

    /// 사용자에게 보여주기 위한
        //게시물 작성자 아이디
        private String mid;
        //게시물 작성자 닉네임
        private String mname;
        //게시물 카테고리 이름
        private String cname;

    /// +댓글 리스트
        private List<ReplyDto> replyDto;


    // dto -> entity 변환 메서드
    public BoardEntity toEntity() {
        return BoardEntity.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .build();
    }
}
