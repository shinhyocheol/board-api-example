package kr.co.springJpaPosts.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.springJpaPosts.board.dto.BoardDto;
import kr.co.springJpaPosts.board.service.BoardService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class BoardController {

	private BoardService boardService;
	
	@GetMapping(value = {"/"})
	public String list(Model model) {
		List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list.html";
	}
	
	@GetMapping(value = {"/post"})
	public String post() {
		return "board/post.html"; 
	}
	
	@PostMapping(value = {"/post"})
    public String write(BoardDto boardDto) {
		boardService.savePost(boardDto);
        return "redirect:/";
    }
	
	@GetMapping(value = {"/post/{id}"})
	public String detail(@PathVariable long id, Model model) {
		BoardDto boardDto = boardService.getPost(id); 
		model.addAttribute("post", boardDto);
		return "board/detail.html";
	}
	
	@GetMapping(value = {"/post/edit/{id}"})
	public String edit(@PathVariable long id, Model model) {
		
		BoardDto boardDto = boardService.getPost(id);
		model.addAttribute("post", boardDto);
		
		return "board/edit.html";
		
	}
	
	@PutMapping(value = {"/post/edit/{id}"})
	public String modify(@PathVariable long id, 
			BoardDto setBoard, 
			Model model) {
		boardService.savePost(setBoard);
		return "redirect:/";
	}
	
	@DeleteMapping(value = {"/post/{id}"})
	public String delete(@PathVariable long id) {
		boardService.deletePost(id);
		return "redirect:/";
	}
	
}
