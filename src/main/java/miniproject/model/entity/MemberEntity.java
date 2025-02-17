package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;
import miniproject.model.dto.MemberDto;

@Entity
@Table(name = "member")
@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class MemberEntity extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(nullable = false , unique = true , columnDefinition = "varchar(30)")
    private String mid;

    @Column(nullable = false , columnDefinition = "varchar(30)")
    private String mpwd;

    @Column(nullable = false , columnDefinition = "varchar(20)")
    private String mname;

    @Column(nullable = false , unique = true , columnDefinition = "varchar(13)")
    private String mphone;

    // entity -> dto 변환 함수
    public MemberDto toDto() {
        return MemberDto.builder()
                .mno(this.mno)
                .mid(this.mid)
                .mpwd(this.mpwd)
                .mname(this.mname)
                .mphone(this.mphone)
                .build();
    }
}
