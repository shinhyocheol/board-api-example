package kr.co.platform;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.platform.api.posts.dto.RegistPostsDto;
import lombok.RequiredArgsConstructor;
import kr.co.platform.api.posts.dto.ModifyPostsDto;
import kr.co.platform.api.posts.dto.RegistCommentDto;

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
class SpringJpaApplicationTests {

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
//	void contextLoads_게시판_글_목록조회() throws Exception{
//
//		mockMvc.perform(get("/posts"))
//		.andDo(print())
//		.andExpect(status().isOk());
//
//	}
//
//	
//	@Test
//	void contextLoads_Posts_상세조회() throws Exception {
//
//		mockMvc.perform(get("/posts/16"))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}
//
//	@Test
//	void contextLoads_게시판_글_등록() throws Exception {
//
//		RegistPostsDto posts = new RegistPostsDto(
//				"게시글과 회원은 연관관계", 
//				"그러므로 땔래야 땔 수 없는 관계이다.");
//
//		String content = objectMapper.writeValueAsString(posts);
//
//		mockMvc.perform(post("/posts")
//				.header("x-access-token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ4LWFjY2Vzcy10b2tlbiIsImV4cCI6MTYyNjcwNzg4MCwidXNlclBrIjoxNCwiZW1haWwiOiJ6a3premgxQG5hdmVyLmNvbSIsIm5pY2tuYW1lIjoi7Zqo67CpIiwicm9sZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XX0.wPRNcQAMK-dGRddPvnEpuyx11AdEBfBhZvXgMSsCrTY")
//				.content(content)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}
//
//	@Test
//	void contextLoads_게시판_글_수정() throws Exception {
//		ModifyPostsDto posts = new ModifyPostsDto((long)3, "제목 수정 테스트", "본문 수정 테스트");
//		String content = objectMapper.writeValueAsString(posts);
//		mockMvc.perform(put("/posts/3")
//				.content(content)
//				.contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}
//
//	@Test
//	void contextLoads_게시판_글_삭제() throws Exception {
//
//		mockMvc.perform(delete("/posts/3"))
//				.andDo(print())
//				.andExpect(status().isOk());
//
//	}
	
	@Test
	void contextLoads_댓글_등록테스트() throws Exception {
		
		RegistCommentDto comment = new RegistCommentDto();
		comment.setComment("대댓글 등록22222222");
		
		comment.setGroupNo((long)0);
		comment.setDepthNo((long)1);
		comment.setPostsId((long)19);
		
		String content = objectMapper.writeValueAsString(comment);
		
		mockMvc.perform(post("/posts/19/comment")
				.content(content)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	
	
}
