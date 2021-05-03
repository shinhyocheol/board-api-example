package kr.co.platform.api.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.platform.api.posts.dto.PostsResDto;
import kr.co.platform.api.posts.dto.PostsSetDto;
import kr.co.platform.util.model.CustomModelMapper;
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

    private CustomModelMapper customModelMapper;

    public Long regPostsService(PostsRegDto regPosts) {

        Long insertId = postsRepository.save(regPosts.toEntity()).getId();

        return insertId;
    }
    
    @Transactional
    public List<PostsResDto> getPostsService() {
        
    	List<Posts> entityList = postsRepository.findAll();

        return entityList.stream()
                .map(entity -> customModelMapper.toDto(entity, PostsResDto.class))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PostsResDto getPostsByIdService(Long id) {
    	
    	Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result Not Found"));
    	
    	return customModelMapper.toDto(entity, PostsResDto.class);
    }

    public Long setPostsService(PostsSetDto setPosts) {

        Long id = postsRepository.save(setPosts.toEntity()).getId();

        return id;
    }

	public void deletePosts(Long id) {

        postsRepository.deleteById(id);

	}

}
