package miniproject.model.repository;

import miniproject.model.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity,Integer> {
    boolean existsByMidAndMpwd(String mid,String mpwd); // 로그인 여부 검사 추상 메서드

    MemberEntity findByMid(String mid);

    MemberEntity findByMnameAndMphone(String mname,String mphone);

    MemberEntity findByMidAndMphone(String mid,String mphone);
}
