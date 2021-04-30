package kr.co.springJpaPosts.api.member.domain.repository;

import kr.co.springJpaPosts.api.member.domain.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Members, Long> {

    Optional<Members> findByEmail(String email);

    @Query(value =
            "select count(*) " +
                    "from members " +
                    "where member_id = :memberId ", nativeQuery = true)
    Integer countByEmail(String email);
}
