package kr.co.platform.api.member.domain.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.entity.PostsComment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EntityListeners(AuditingEntityListener.class)
public class Members{

	@Id
	@GeneratedValue
	private Long id;

	@Column(length = 100, nullable = false)
	private String email;

	@Column(length = 200, nullable = false)
	private String password;

	@Column(length = 100, nullable = false)
	private String name;

	@Column(length = 100, nullable = false)
	private String mobile;

	@Column(length = 100, nullable = true)
	private String nickname;
	
	@Column(length = 200, nullable = true)
	private String profile;

	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	// 게시글 Entity 연관관계 설정(One(회원 Entity) To Many(게시글 Entity)
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Posts> posts = new ArrayList<Posts>();

	// 댓글 Entity 연관관계 설정(One(회원 Entity) To Many(댓글 Entity)
	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PostsComment> postsComment = new ArrayList<PostsComment>();

	@Builder
	public Members(Long id, String email, 
			String password, String name, 
			String mobile, String nickname) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.mobile = mobile;
		this.nickname = nickname;
	}


}
