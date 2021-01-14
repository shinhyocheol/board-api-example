package kr.co.springJpaPosts.posts.contorller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PostsController {

	@GetMapping(value = {"/"})
	public String list() {
		return "board/list.html";
	}
	
	@GetMapping(value = {"/post"})
	public String post() {
		return "board/post.html"; 
	}
	
}
