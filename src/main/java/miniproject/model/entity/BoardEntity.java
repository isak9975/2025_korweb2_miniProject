package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import miniproject.model.dto.BoardDto;

import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "board")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardEntity extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno; // 게시물 번호

    @Column(columnDefinition = "varchar(255)" , nullable = false)
    private String btitle; // 게시물 제목

    @Column(columnDefinition = "longtext")
    private String bcontent; // 게시물 내용

    @Column
    private int bview; // 게시물 조회수

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity; // 게시물 작성자 번호(fk)

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cno")
    private CategoryEntity categoryEntity; // 게시물 카테고리 번호(fk)

    //Dto로 변환
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .btitle(this.btitle)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno(this.memberEntity.getMno())
                .cno(this.categoryEntity.getCno())
                .mid(this.memberEntity.getMid())
                .mname(this.memberEntity.getMname())
                .cname(this.categoryEntity.getCname())
                //날짜 데이터 값이 너무 지저분하게 나와서 => 2025-02-13T19:01:10
                //format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) 사용하여 제한하여 보냄.
                //to String 안하면 Dto(String)에서 못받음.
                .cdate(this.getCdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString())
                .build();
    }


}
