package kr.co.platform.api.posts.domain.repository.support;

import com.querydsl.core.alias.Alias;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPQLQuery;
import kr.co.platform.api.posts.domain.entity.PostsComment;
import kr.co.platform.api.posts.domain.repository.CommentRepository;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.JPQLQueryFactory;

import java.util.List;

import static kr.co.platform.api.posts.domain.entity.QPostsComment.postsComment;
import static kr.co.platform.api.member.domain.entity.QMembers.members;

@Repository
public class CustomCommentRepositoryImpl extends QuerydslRepositorySupport implements CustomCommentRepository {

	private final JPQLQueryFactory queryFactory;
	
	public CustomCommentRepositoryImpl(JPQLQueryFactory queryFactory) {
		super(PostsComment.class);
		this.queryFactory = queryFactory;
	}
	
//	public List<PostsComment> findByPostsId(Long postsId) {
//
//		JPQLQuery<PostsComment> result = queryFactory
//				.select(Projections.fields(PostsComment.class,
//						postsComment.commentId, postsComment.comment,
//						postsComment.targetNickname, postsComment.groupNo,
//						postsComment.depthNo, postsComment.createdDate,
//						postsComment.modifiedDate,
//						members
////						ExpressionUtils.as(members.id, "memberId"),
////						ExpressionUtils.as(members.nickname, "memberNickname")
//						))
//				.from(postsComment)
//					.leftJoin(members)
//						.on(postsComment.member.id.eq(members.id))
//				.where(postsComment.postsId.eq(postsId));
//
//		return result.fetch();
//	}
}
