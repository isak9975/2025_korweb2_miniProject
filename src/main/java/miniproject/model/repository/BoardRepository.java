package miniproject.model.repository;

import miniproject.model.entity.BoardEntity;
import miniproject.model.entity.MemberEntity;
import miniproject.model.entity.ReplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity,Integer> {
    // MemberEntity와 관련된 ReplyEntity 데이터들을 찾는 추상 메서드
    List<BoardEntity> findByMemberEntity(MemberEntity memberEntity);
}
