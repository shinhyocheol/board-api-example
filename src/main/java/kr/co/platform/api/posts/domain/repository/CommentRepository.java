package kr.co.platform.api.posts.domain.repository;

import kr.co.platform.api.posts.domain.repository.support.CustomCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.platform.api.posts.domain.entity.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> , CustomCommentRepository {

	List<Comment> findByPostsId(Long id);

	
}
