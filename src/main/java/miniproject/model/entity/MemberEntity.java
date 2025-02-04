package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member")
@Getter @Setter @ToString @Builder @NoArgsConstructor @AllArgsConstructor
public class MemberEntity extends BassTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;

    @Column(nullable = false , unique = true , columnDefinition = "varchar(30)")
    private String mid;

    @Column(nullable = false , columnDefinition = "varchar(30)")
    private String mpwd;

    @Column(nullable = false , columnDefinition = "varchar(20)")
    private String mname;

    @Column(nullable = false , unique = true , columnDefinition = "varchar(50)")
    private String memail;


}
