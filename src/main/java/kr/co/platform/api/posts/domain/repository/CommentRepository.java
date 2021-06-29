package kr.co.platform.api.posts.domain.repository;

import kr.co.platform.api.posts.domain.repository.support.CustomCommentRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import kr.co.platform.api.posts.domain.entity.PostsComment;

import java.util.List;

public interface CommentRepository extends JpaRepository<PostsComment, Long> , CustomCommentRepository {

}
