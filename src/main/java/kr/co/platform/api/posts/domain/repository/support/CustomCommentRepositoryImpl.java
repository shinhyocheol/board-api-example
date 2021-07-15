package kr.co.platform.api.posts.domain.repository.support;

import com.querydsl.core.types.ExpressionUtils;

import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;

import kr.co.platform.api.posts.domain.entity.PostsComment;
import lombok.ToString;

import java.util.List;

import static kr.co.platform.api.member.domain.entity.QMembers.members;
import static kr.co.platform.api.posts.domain.entity.QPostsComment.postsComment;
import static kr.co.platform.api.posts.domain.entity.QPosts.posts;

@Repository
public class CustomCommentRepositoryImpl extends QuerydslRepositorySupport implements CustomCommentRepository {

	private final JPQLQueryFactory queryFactory;
	
	public CustomCommentRepositoryImpl(JPQLQueryFactory queryFactory) {
		super(PostsComment.class);
		this.queryFactory = queryFactory;
	}
	
	public List<PostsComment> findByPostsId(Long postsId) {

		List<PostsComment> result = queryFactory
				.select(
						Projections.fields(PostsComment.class,
						postsComment.commentId, postsComment.comment,
						postsComment.targetNickname, postsComment.groupNo,
						postsComment.depthNo, postsComment.createdDate,
						postsComment.modifiedDate,
						postsComment.posts,
						postsComment.member
					))
				.from(postsComment)
					.leftJoin(postsComment.member, members)
					.leftJoin(postsComment.posts, posts)
				.where(postsComment.posts.id.eq(postsId))
				.fetch();
				
		return result;
	}
}
