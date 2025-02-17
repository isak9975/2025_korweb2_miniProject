package miniproject.model.repository;

import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.MemberEntity;
import miniproject.model.entity.ReplyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    // MemberEntity와 관련된 ReplyEntity 데이터들을 찾는 추상 메서드
    List<BoardEntity> findByMemberEntity(MemberEntity memberEntity);


    //[1] cno 로 레코드 조회
    //Page<BoardEntity> findByCno(int cno, Pageable pageable);
    //만약에 cno가 참조키(fk) 일때는 fk필드명 넣지 않고 엔티티 필드명 사용
    //자바엔티티필드명_참조엔티티필드명 //findByCategoryEntity_Cno
    Page< BoardEntity > findByCategoryEntity_Cno(int cno, Pageable pageable);


}
