package kr.co.platform.api.posts.dto;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.entity.PostsComment;
import kr.co.platform.util.auth.AuthUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegistCommentDto {
	
	private String comment;
	
	private Long groupNo;
	
	private Long depthNo;
	
	private Long postsId;
	
	public PostsComment toEntity() {
		
		Posts posts = Posts.builder().id(postsId).build();
		Members member = Members.builder().id((long)14).build();
		
		PostsComment build = PostsComment.builder()
				.comment(comment)
				.groupNo(groupNo)
				.depthNo(depthNo)
				.posts(posts)
				.member(member)
				.build();
		
		return build;
	}
	
}
