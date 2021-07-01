package kr.co.platform.api.posts.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentResDto {
	
	private Long commentId;
	
	private String comment;
	
	private Long postsId;
	
	private Long groupNo;
	
	private Long depthNo;
	
	private Long targetNickname;
	
	private LocalDateTime createdDate;
	
	private LocalDateTime modifiedDate;
	
	private String memberId;
	
	private String memberNickname;
	
}
