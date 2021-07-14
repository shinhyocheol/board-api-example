package kr.co.platform.api.posts.domain.repository.support;


import java.util.List;

import kr.co.platform.api.posts.domain.entity.PostsComment;

public interface CustomCommentRepository {

    public List<PostsComment> findByPostsId(Long postsId);

}
