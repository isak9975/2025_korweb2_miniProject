package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import miniproject.model.dto.ReplyDto;

@Entity
@Table(name = "reply")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno; // 댓글 번호

    @Column(columnDefinition = "varchar(255)" , nullable = false)
    private String rcontent; // 댓글 내용

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity; // 댓글 게시물 번호(fk)

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mno")
    private MemberEntity memberEntity; // 댓글 작성자 번호(fk)

    // entity -> DTO
    public ReplyDto toDto() {
        return ReplyDto.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .bno(this.boardEntity.getBno())
                .mno(this.memberEntity.getMno())
                .mid(this.memberEntity.getMid())
                .build();
    }
}
