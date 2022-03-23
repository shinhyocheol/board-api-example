package kr.co.platform.api.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.platform.api.posts.domain.entity.PostsComment;
import kr.co.platform.api.posts.dto.*;
import kr.co.platform.exception.custom.ApiOtherException;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.repository.CommentRepository;
import kr.co.platform.api.posts.domain.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service("postsService")
@RequiredArgsConstructor
public class PostsService {
	
    private final PostsRepository postsRepository;
    
    private final CommentRepository commentRepository;

    private final ModelMapper modelMapper;

    public Long regPosts(RegistPostsDto regPosts) {

        return postsRepository.save(regPosts.toEntity()).getId();
    }
    
    public PageImpl<PostsResDto> getPosts(PageRequest pageble) {

    	Page<Posts> entityList = postsRepository.findAll(pageble);

    	List<PostsResDto> result = entityList.stream()
                .map(entity -> modelMapper.map(entity, PostsResDto.class))
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageble, entityList.getTotalElements());
    }
    
    public PostsResDto getPostsById(Long id) {
    	
    	Posts postsEntity = postsRepository.findById(id)
                .orElseThrow(() -> new ApiOtherException("Posts Result Not Found"));
    	
    	PostsResDto result = modelMapper.map(postsEntity, PostsResDto.class);
    	
    	// 게시글에 작성된 댓글 데이터 조회
        result.setComments(commentRepository.findByPostsId(id).stream()
    			.map(commentEntity -> modelMapper.map(commentEntity, CommentResDto.class))
    			.collect(Collectors.toList()));

    	return result;
    }

    @Transactional
    public void setPosts(ModifyPostsDto setPosts) {

        // 게시글 데이터 먼저 뽑아온 후
        Posts entity = postsRepository.findById(setPosts.getId())
                .orElseThrow(() -> new EntityNotFoundException("해당 포스트가 존재하지 않습니다."));

        // 메소드를 통해 해당 entity의 수정하고자 하는 데이터를 파라미터로 전달
        // @Transactional 어노테이션으로 entity는 영속성을 가지게 되고, 변화가 일어나는 순간
        // 데이터베이스에 반영한다.
        entity.modifyPostsById(setPosts.getId(), setPosts.getTitle(), setPosts.getContent());


    }

	public void delPosts(Long id) {

        postsRepository.deleteById(id);

	}

	@Transactional
	public void regCommentByPosts(RegistCommentDto regComment) {
		// 댓글 등록
        PostsComment comment = commentRepository.save(regComment.toEntity());

        // 등록한 댓글의 ID를 해당 댓글의 그룹번호로 지정
        comment.saveCommentGroupNo(comment.getCommentId());
	}

    public void regReplyByComment(RegistReplyDto regReply) {
        // 대댓글 등록
        commentRepository.save(regReply.toEntity());

    }
}
