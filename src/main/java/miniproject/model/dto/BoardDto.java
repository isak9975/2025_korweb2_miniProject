package miniproject.model.dto;

import lombok.*;
import miniproject.model.entity.BoardEntity;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
public class BoardDto {
    private int bno;
    private String btitle;
    private String bcontent;
    private int bview;

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
