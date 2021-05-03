package kr.co.platform.api.posts.dto;

import kr.co.platform.api.posts.domain.entity.Posts;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class PostsRegDto {

	@NotBlank(message = "'author' is a required input value")
	private String author;

	@NotBlank(message = "'title' is a required input value")
	private String title;

	@NotBlank(message = "'content' is a required input value")
	private String content;

	public Posts toEntity() {
		Posts build = Posts.builder()
				.author(author)
				.title(title)
				.content(content)
				.build();
		return build;
	}

}