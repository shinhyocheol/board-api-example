package kr.co.platform.api.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.platform.api.posts.domain.entity.PostsComment;
import kr.co.platform.api.posts.dto.PostsResDto;
import kr.co.platform.api.posts.dto.CommentResDto;
import kr.co.platform.api.posts.dto.ModifyPostsDto;
import kr.co.platform.model.CustomModelMapper;
import kr.co.platform.util.advice.exception.ApiOtherException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import kr.co.platform.api.posts.dto.RegistPostsDto;
import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.repository.CommentRepository;
import kr.co.platform.api.posts.domain.repository.PostsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service("postsService")
@AllArgsConstructor
@Slf4j
public class PostsService {
	
    private PostsRepository postsRepository;
    
    private CommentRepository commentRepository;

    private CustomModelMapper modelMapper;

    public Long regPostsService(RegistPostsDto regPosts) {

        Long insertId = postsRepository.save(regPosts.toEntity()).getId();

        return insertId;
    }
    
    public PageImpl<PostsResDto> getPostsService(PageRequest pageble) {

    	Page<Posts> entityList = postsRepository.findAll(pageble);

    	List<PostsResDto> result = entityList.stream()
                .map(entity -> modelMapper.toDto(entity, PostsResDto.class))
                .collect(Collectors.toList());

        return new PageImpl<PostsResDto>(result, pageble, entityList.getTotalElements());
    }
    
    public PostsResDto getPostsByIdService(Long id) {
    	
    	Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new ApiOtherException("Posts Result Not Found"));
    	
    	PostsResDto result = modelMapper.toDto(entity, PostsResDto.class);
    	
//    	List<PostsComment> commentEntitys = commentRepository.findByPostsId(id);
//    	result.setComments(commentEntitys);

    	// log.debug("CommentEntity : {}", commentEntitys);
 	
    	// result.setComments(commentEntitys.stream()
		// 									.map(commentEntity -> modelMapper.toDto(commentEntity, CommentResDto.class))
    	//    								.collect(Collectors.toList()));

    	return result;
    }

    public void setPostsService(ModifyPostsDto setPosts) {

        postsRepository.save(setPosts.toEntity());

    }

	public void delPostsService(Long id) {

        postsRepository.deleteById(id);

	}

}
