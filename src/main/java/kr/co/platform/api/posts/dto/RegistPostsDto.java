package kr.co.platform.api.posts.dto;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.util.auth.AuthUtil;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class RegistPostsDto {

	@NotBlank(message = "'title' is a required input value")
	private String title;

	@NotBlank(message = "'content' is a required input value")
	private String content;

	public Posts toEntity() {
		
		Members memberEntity = Members
				.builder()
				.id(AuthUtil.getId())
				.build();
		
		Posts postsBuild = Posts.builder()
				.author(AuthUtil.getNickname())
				.title(title)
				.content(content)
				.member(memberEntity)
				.build();
		
		return postsBuild;
	}

}