package kr.co.springJpaPosts.posts.dto;

import java.time.LocalDateTime;

import kr.co.springJpaPosts.posts.domain.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostsRegDto {

	private Long id;
	private String author;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;

	public Posts toEntity() {
		Posts build = Posts.builder()
				.id(id)
				.author(author)
				.title(title)
				.content(content)
				.build();
		return build;
	}

}