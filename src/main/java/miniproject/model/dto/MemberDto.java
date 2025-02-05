package miniproject.model.dto;

import lombok.*;
import miniproject.model.entity.MemberEntity;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class MemberDto {
    private int mno;
    private String mid;
    private String mpwd;
    private String mname;
    private String memail;

    // dto -> entity 변환 함수
    public MemberEntity toEntity() {
        return MemberEntity.builder()
                .mid(this.mid)
                .mpwd(this.mpwd)
                .mname(this.mname)
                .memail(this.memail)
                .build();
    }
}
