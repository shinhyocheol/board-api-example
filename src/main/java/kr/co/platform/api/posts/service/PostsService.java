package kr.co.platform.api.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import kr.co.platform.api.posts.domain.entity.PostsComment;
import kr.co.platform.api.posts.dto.*;
import kr.co.platform.model.CustomModelMapper;
import kr.co.platform.util.advice.exception.ApiOtherException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.repository.CommentRepository;
import kr.co.platform.api.posts.domain.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Service("postsService")
@RequiredArgsConstructor
public class PostsService {
	
    private final PostsRepository postsRepository;
    
    private final CommentRepository commentRepository;

    private final CustomModelMapper modelMapper;

    public Long regPosts(RegistPostsDto regPosts) {
    	
        Long insertId = postsRepository.save(regPosts.toEntity()).getId();

        return insertId;
    }
    
    public PageImpl<PostsResDto> getPosts(PageRequest pageble) {

    	Page<Posts> entityList = postsRepository.findAll(pageble);

    	List<PostsResDto> result = entityList.stream()
                .map(entity -> modelMapper.toMapping(entity, PostsResDto.class))
                .collect(Collectors.toList());

        return new PageImpl<PostsResDto>(result, pageble, entityList.getTotalElements());
    }
    
    public PostsResDto getPostsById(Long id) {
    	
    	Posts postsEntity = postsRepository.findById(id)
                .orElseThrow(() -> new ApiOtherException("Posts Result Not Found"));
    	
    	PostsResDto result = modelMapper.toMapping(postsEntity, PostsResDto.class);
    	
    	// 게시글에 작성된 댓글 데이터 조회
    	List<PostsComment> commentEntitys = commentRepository.findByPostsId(id);
    	
    	result.setComments(commentEntitys.stream()
    			.map(commentEntity -> modelMapper.toMapping(commentEntity, CommentResDto.class))
    			.collect(Collectors.toList()));

    	return result;
    }

    public void setPosts(ModifyPostsDto setPosts) {

        postsRepository.save(setPosts.toEntity());

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
