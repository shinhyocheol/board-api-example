package kr.co.springJpaPosts.posts.service;

import java.util.List;
import java.util.stream.Collectors;

import kr.co.springJpaPosts.util.object.ObjectUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.springJpaPosts.posts.dto.PostsDto;
import kr.co.springJpaPosts.posts.domain.entity.Posts;
import kr.co.springJpaPosts.posts.domain.repository.PostsRepository;
import lombok.AllArgsConstructor;

@Service("postsService")
@AllArgsConstructor
public class PostsService {
	
    private PostsRepository postsRepository;

    private ObjectUtil objectUtil;

    @Transactional
    public Long savePosts(
            PostsDto postsDto) {

    	return postsRepository.save(postsDto.toEntity()).getId();
    }
    
    @Transactional
    public List<PostsDto> getPostsList() {
        
    	List<Posts> entityList = postsRepository.findAll();

        return entityList.stream()
                .map(entity -> objectUtil.toDto(entity, PostsDto.class))
                .collect(Collectors.toList());
    }
    
    @Transactional
    public PostsDto getPosts(Long id) {
    	
    	Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result Not Found"));
    	
    	return objectUtil.toDto(entity, PostsDto.class);
    }

	public void deletePosts(Long id) {

        postsRepository.deleteById(id);

	}
    
    
}
