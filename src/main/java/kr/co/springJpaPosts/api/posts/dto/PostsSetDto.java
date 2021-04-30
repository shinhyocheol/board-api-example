package kr.co.springJpaPosts.api.posts.dto;

import kr.co.springJpaPosts.api.posts.domain.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostsSetDto {

    private Long id;
    private String title;
    private String content;

    public Posts toEntity() {
        Posts build = Posts.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        return build;
    }

}
