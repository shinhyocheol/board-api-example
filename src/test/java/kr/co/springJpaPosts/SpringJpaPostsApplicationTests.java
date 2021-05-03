package kr.co.springJpaPosts;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.springJpaPosts.api.posts.dto.PostsRegDto;
import kr.co.springJpaPosts.api.posts.dto.PostsSetDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class SpringJpaPostsApplicationTests {

	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private WebApplicationContext context;

	@BeforeAll
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
				.addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print())
				.build();
	}

//	@Test
//	void contextLoads_게시판_글_등록() throws Exception {
//
//		PostsRegDto posts = new PostsRegDto("작성자", "테스트 제목", "테스트 본문");
//
//		String content = objectMapper.writeValueAsString(posts);
//
//		mockMvc.perform(post("/posts")
//				.content(content)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}


//	@Test
//	void contextLoads_게시판_글_목록조회() throws Exception{
//
//		mockMvc.perform(get("/posts"))
//		.andDo(print())
//		.andExpect(status().isOk());
//
//	}

//	@Test
//	void contextLoads_게시판_글_상세조회() throws Exception {
//
//		mockMvc.perform(get("/posts/1"))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}

//	@Test
//	void contextLoads_게시판_글_수정() throws Exception {
//		PostsSetDto posts = new PostsSetDto((long)1, "수정자", "제목 수정 테스트", "본문 수정 테스트");
//		String content = objectMapper.writeValueAsString(posts);
//		mockMvc.perform(put("/posts/1")
//				.content(content)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}

//	@Test
//	void contextLoads_게시판_글_삭제() throws Exception {
//
//		mockMvc.perform(delete("/posts/1"))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}


}
