package kr.co.platform.api.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.platform.api.posts.dto.PostsResDto;
import kr.co.platform.api.posts.dto.PostsSetDto;
import kr.co.platform.util.advice.exception.ApiOtherException;
import kr.co.platform.util.empty.Assert;
import kr.co.platform.util.model.CustomModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.platform.api.posts.dto.PostsRegDto;
import kr.co.platform.api.posts.domain.entity.Posts;
import kr.co.platform.api.posts.domain.repository.PostsRepository;
import lombok.AllArgsConstructor;

@Service("postsService")
@AllArgsConstructor
public class PostsService {
	
    private PostsRepository postsRepository;

    private CustomModelMapper modelMapper;

    public Long regPostsService(PostsRegDto regPosts) {

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
    	
    	return modelMapper.toDto(entity, PostsResDto.class);
    }

    public void setPostsService(PostsSetDto setPosts) {

        postsRepository.save(setPosts.toEntity());

    }

	public void delPostsService(Long id) {

        postsRepository.deleteById(id);

	}

}
