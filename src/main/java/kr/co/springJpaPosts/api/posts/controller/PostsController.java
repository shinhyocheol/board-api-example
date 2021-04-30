package kr.co.springJpaPosts.api.posts.controller;

import java.util.List;


import kr.co.springJpaPosts.api.posts.dto.PostsRegDto;
import kr.co.springJpaPosts.api.posts.dto.PostsResDto;
import kr.co.springJpaPosts.api.posts.dto.PostsSetDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import kr.co.springJpaPosts.api.posts.service.PostsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = {"/posts"}, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PostsController {

	private PostsService postsService;
	
	@GetMapping(value = {""})
	public ResponseEntity<List<PostsResDto>> getPosts() {

		return ResponseEntity.ok()
				 			 .body(postsService.getPostsService());
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
	public ResponseEntity<Long> setPosts(
			@PathVariable Long id,
			@Valid @RequestBody PostsSetDto setPosts) throws Exception {

		return ResponseEntity.ok()
							 .body(postsService.setPostsService(setPosts));
	}

	@DeleteMapping(value = {"/post/{id}"})
	public ResponseEntity<String> delPosts(
			@PathVariable Long id) {

		postsService.deletePosts(id);

		return ResponseEntity.ok()
							 .body("SUCCESS");
	}
	
}
