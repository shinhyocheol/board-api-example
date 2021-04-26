package kr.co.springJpaPosts.posts.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.springJpaPosts.posts.domain.entity.Posts;

/**
 * Repository는 데이터 조작을 담당하며, Jpa Repository를 상속받는다.
 * JpaRepository의 값은 매핑할 Entity와 ID의 타입이다.
 */
public interface PostsRepository extends JpaRepository<Posts, Long>{

	
}
