package kr.co.platform.api.posts.domain.repository.support;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;

import kr.co.platform.api.posts.domain.entity.PostsComment;

import java.util.List;

import static kr.co.platform.api.member.domain.entity.QMembers.members;
import static kr.co.platform.api.posts.domain.entity.QPostsComment.postsComment;

@Repository
public class CustomCommentRepositoryImpl extends QuerydslRepositorySupport implements CustomCommentRepository {

	private final JPQLQueryFactory queryFactory;
	
	public CustomCommentRepositoryImpl(JPQLQueryFactory queryFactory) {
		super(PostsComment.class);
		this.queryFactory = queryFactory;
	}
	
	public List<PostsComment> findByPostsId(Long postsId) {

		JPQLQuery<PostsComment> result = queryFactory
				.select(Projections.fields(PostsComment.class,
						postsComment.commentId, postsComment.comment,
						postsComment.targetNickname, postsComment.groupNo,
						postsComment.depthNo, postsComment.createdDate,
						postsComment.modifiedDate,
						ExpressionUtils.as(postsComment.member.id, "memberId"),
						ExpressionUtils.as(postsComment.member.email, "memberEmail"),
						ExpressionUtils.as(postsComment.member.nickname, "memberNickname")
						))
				.from(postsComment)
					.innerJoin(postsComment.member, members)
				.where(postsComment.posts.id.eq(postsId));

		return result.fetch();
	}
}
