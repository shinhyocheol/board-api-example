package kr.co.platform.api.posts.controller;

import java.util.List;


import kr.co.platform.api.posts.dto.PostsRegDto;
import kr.co.platform.api.posts.dto.PostsResDto;
import kr.co.platform.api.posts.dto.PostsSetDto;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import kr.co.platform.api.posts.service.PostsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = {"/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PostsController {

	private PostsService postsService;
	
	@GetMapping(value = {""})
	public ResponseEntity<PageImpl<PostsResDto>> getPosts(
			@RequestParam Integer page) {
		PageRequest pageble = PageRequest.of(page - 1, 10);
		return ResponseEntity.ok()
				 			 .body(postsService.getPostsService(pageble));
	}

	@PostMapping(value = {""})
	public ResponseEntity<Long> regPosts(
			@Valid @RequestBody PostsRegDto regPosts) throws Exception {

		return ResponseEntity.ok()
							 .body(postsService.regPostsService(regPosts));
	}

	@GetMapping(value = {"/{id}"})
	public ResponseEntity<PostsResDto> getPostsDetail(
			@PathVariable Long id) {

		return ResponseEntity.ok()
							 .body(postsService.getPostsByIdService(id));
	}

	@PutMapping(value = {"/{id}"})
	public ResponseEntity<String> setPosts(
			@PathVariable Long id,
			@Valid @RequestBody PostsSetDto setPosts) throws Exception {

		postsService.setPostsService(setPosts);

		return ResponseEntity.ok()
							 .body("UPDATE SUCCESS");
	}

	@DeleteMapping(value = {"/{id}"})
	public ResponseEntity<String> delPosts(
			@PathVariable Long id) {

		postsService.delPostsService(id);

		return ResponseEntity.ok()
							 .body("DELETE SUCCESS");
	}
	
}
