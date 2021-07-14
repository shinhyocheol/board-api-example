package kr.co.platform.api.posts.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
import lombok.ToString;

@DynamicUpdate
@DynamicInsert
@Getter
@Entity
@Table(name = "posts_comment")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Comment {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long commentId;
	
	@Column(length = 200, nullable = true)
	private String comment;
	
	@Column(nullable = true)
	private Long groupNo;
	
	@Column(nullable = false)
	private Long depthNo;
	
	@Column(length = 50, nullable = true)
	private String targetNickname;
	
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;
	
	@LastModifiedDate
	private LocalDateTime modifiedDate;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    private Posts posts;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Members member;
    
    @Builder
    public Comment(
    		Long commentId, String comment,
    		Long groupNo, Long depthNo,
    		String targetNickname) {
    	
    	this.commentId = commentId;
    	this.comment = comment;
    	this.groupNo = groupNo;
    	this.depthNo = depthNo;
    	this.targetNickname = targetNickname;
    	
    }
    
}
