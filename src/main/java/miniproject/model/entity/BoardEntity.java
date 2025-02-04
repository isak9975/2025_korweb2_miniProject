package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

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


}
