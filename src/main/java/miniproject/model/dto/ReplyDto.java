package miniproject.model.dto;

import lombok.*;
import miniproject.model.entity.MemberEntity;
import miniproject.model.entity.ReplyEntity;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private int rno;
    private String rcontent;
    private int bno;
    private int mno;

    private String mid;

    // DTO -> entity
    public ReplyEntity toEntity() {
        return ReplyEntity.builder()
                .rno(this.rno)
                .rcontent(this.rcontent)
                .build();
    }
}
