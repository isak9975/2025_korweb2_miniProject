package miniproject.model.dto;

import lombok.*;
import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.MemberEntity;

@Setter@Getter@Builder
@AllArgsConstructor@NoArgsConstructor
public class BaordDto {

    //게시물 번호(PK)
    private int bno;

    //게시물 제목
    private String btitle;

    //게시물 내용
    private String bcontent;

    //게시물 조회수
    private int bview;

    //게시물 작성자 번호(FK)
    private int mno;

    //게시물 카테고리 번호(FK)
    private int cno;


    //엔티티로 변환
    public BoardEntity toEntity(){
        return BoardEntity.builder().bno(this.bno).bcontent(this.bcontent).btitle(this.btitle).bview(this.bview).build();
    }


}
