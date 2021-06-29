package kr.co.platform.api.posts.domain.repository.support;

import kr.co.platform.api.posts.domain.entity.PostsComment;

import java.util.List;

public interface CustomCommentRepository {

    public List<PostsComment> findByPostsId(Long postsId);

}
