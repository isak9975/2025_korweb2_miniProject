package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "category")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEntity extends BaseTime {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cno; // 카테고리 번호

    @Column(columnDefinition = "varchar(50)" , nullable = false)
    private String cname; // 카테고리 이름
}
