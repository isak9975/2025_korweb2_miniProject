package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import miniproject.model.dto.BoardDto;

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
                .cname(this.categoryEntity.getCname())
                .build();
    }


}
