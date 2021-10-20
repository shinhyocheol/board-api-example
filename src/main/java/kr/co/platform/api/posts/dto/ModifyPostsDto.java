package kr.co.platform.api.posts.dto;

import kr.co.platform.api.member.domain.entity.Members;

import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.util.auth.AuthUtil;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class ModifyPostsDto {


    @Min(1)
    private Long id;

    @NotBlank(message = "'title' is a required input value")
    private String title;

    @NotBlank(message = "'content' is a required input value")
    private String content;

    public Posts toEntity() {
    	
    	Members memberEntity = Members
    			.builder()
    			.id(AuthUtil.getId())
    			.build();
    	
        Posts build = Posts.builder()
                .id(id)
                .author(memberEntity.getNickname())
                .title(title)
                .content(content)
                .member(memberEntity)
                .build();
        return build;
    }

}
