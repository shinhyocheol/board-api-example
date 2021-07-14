package kr.co.platform.api.posts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.posts.domain.entity.Comment;

@Getter
@Setter
@NoArgsConstructor
public class PostsResDto {

    private Long id;
    
    private String author;
    
    private String title;
    
    private String content;
    
    private LocalDateTime createdDate;
    
    private LocalDateTime modifiedDate;
    
    private Long memberId;
    
    private String memberEmail;
    
    private List<CommentResDto> comments;
}
