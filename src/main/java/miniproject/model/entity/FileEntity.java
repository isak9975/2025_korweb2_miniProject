package miniproject.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "file")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fno; // 파일 번호

    @Column(columnDefinition = "varchar(255)" , nullable = false)
    private String fname; // 파일 명

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bno")
    private BoardEntity boardEntity;


}
