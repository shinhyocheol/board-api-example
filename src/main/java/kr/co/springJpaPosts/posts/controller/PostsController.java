package kr.co.springJpaPosts.posts.controller;

import java.util.List;


import kr.co.springJpaPosts.posts.dto.PostsResDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import kr.co.springJpaPosts.posts.dto.PostsDto;
import kr.co.springJpaPosts.posts.service.PostsService;
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

	@GetMapping(value = {"/{id}"})
	public ResponseEntity<PostsResDto> getPostsDetail(
			@PathVariable Long id) {

		return ResponseEntity.ok()
							 .body(postsService.getPostsByIdService(id));
	}

//	@GetMapping(value = {"/post/{id}"})
//	public String detail(
//			@PathVariable Long id,
//			Model model) {
//
//		BoardDto boardDto = boardService.getPosts(id);
//		model.addAttribute("post", boardDto);
//
//		return "board/detail.html";
//	}
//
//
//	@PutMapping(value = {"/post/edit/{id}"})
//	public String modify(
//			@PathVariable Long id,
//			BoardDto setBoard,
//			Model model) {
//
//		boardService.savePosts(setBoard);
//
//		return "redirect:/";
//	}
//
//	@DeleteMapping(value = {"/post/{id}"})
//	public String delete(
//			@PathVariable Long id) {
//
//		boardService.deletePosts(id);
//
//		return "redirect:/";
//	}
	
}
