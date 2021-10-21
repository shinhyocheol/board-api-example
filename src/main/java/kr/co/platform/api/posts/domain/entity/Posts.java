package kr.co.platform.api.posts.domain.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.co.platform.api.member.domain.entity.Members;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Posts{

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @Column(length = 1, columnDefinition = "char(1) default 'N'")
    private String isDeleted;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;
    
    // 댓글 Entity 연관관계 설정(One(게시글 Entity) To Many(댓글 Entity)
    @OneToMany(mappedBy = "posts", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostsComment> comment = new ArrayList<PostsComment>(); 
    
    @Builder
    public Posts(Long id, String title, String content, Members member) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.member = member;
    }

    // 게시글 수정
    public void modifyPostsById(
            Long id,
            String title,
            String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
