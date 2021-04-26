package kr.co.springJpaPosts.posts.controller;

import java.util.List;


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
	public ResponseEntity<List<PostsDto>> getPosts() {

		return ResponseEntity.ok()
				 			 .body(postsService.getPostsList());
	}
	
//	@GetMapping(value = {"/post"})
//	public String post() {
//
//		return "board/post.html";
//	}
//
//	@PostMapping(value = {"/post"})
//    public String write(BoardDto boardDto) {
//
//		boardService.savePosts(boardDto);
//
//		return "redirect:/";
//    }
//
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
//	@GetMapping(value = {"/post/edit/{id}"})
//	public String edit(
//			@PathVariable Long id,
//			Model model) {
//
//		BoardDto boardDto = boardService.getPosts(id);
//		model.addAttribute("post", boardDto);
//
//		return "board/edit.html";
//
//	}
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
