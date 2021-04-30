package kr.co.springJpaPosts.api.sign.controller;

import javax.validation.Valid;

import kr.co.springJpaPosts.api.sign.dto.AuthenticationDto;
import kr.co.springJpaPosts.api.sign.dto.JoinDto;
import kr.co.springJpaPosts.api.sign.dto.LoginDto;
import kr.co.springJpaPosts.api.sign.service.SignService;
import kr.co.springJpaPosts.util.auth.CustomUserDetails;
import kr.co.springJpaPosts.util.auth.JwtAuthProvider;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@CrossOrigin(originPatterns = "*")
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

	private SignService apiSignService;
	
	private JwtAuthProvider jwtProvider;

	@PostMapping(value = {"signup"})
	public ResponseEntity<String> appJoin(
			@Valid @RequestBody JoinDto joinDto) throws Exception {

		apiSignService.joinService(joinDto);

		return ResponseEntity.ok()
				.body("SUCCESS");
	}
	
	/**
	 * @method 설명 : 로그인
	 * @param loginDto
	 * @return : 회원인증토큰, 회원정보
	 * @throws Exception
	 */
	@PostMapping(value = {"/signin/{UUID}"})
	public ResponseEntity<AuthenticationDto> appLogin(
			@Valid @RequestBody LoginDto loginDto,
			@PathVariable String UUID) throws Exception {

		AuthenticationDto authentication = apiSignService.loginService(loginDto);
		/**
		 * 토큰발급을 위한 데이터는 UserDetails에서 담당
		 * 따라서 UserDetails를 세부 구현한 CustomUserDetails로 회원정보 전달
		 */
		CustomUserDetails user = new CustomUserDetails(
				authentication.getId(), 			// 회원 등록번호
				authentication.getEmail());				// 회원 아이디

		return ResponseEntity.ok()
				.header("x-access-token", jwtProvider
						.createToken(
								user.getUserPk(),
								user.getUsername(),
								user.getRoles()))
				.body(authentication);
	}
}
