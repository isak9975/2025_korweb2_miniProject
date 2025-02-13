package miniproject.model.dto;

import lombok.*;

@Setter@Builder@Getter
@AllArgsConstructor@NoArgsConstructor
public class PageDto {

    private long totalcount; // 조회된 자료의 개수

    private int page; // 현재 페이지 번호

    private int totalpage; // 전체 페이지 번호

    private int startbtn; // 조회 페이지의 페이징 버튼 시작 번호

    private int endbtn; // 조회 페이지의 페이징 버튼 끝 번호

    //내용
    private Object data;
}
