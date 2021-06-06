package kr.co.platform.api.sign.controller;

import javax.validation.Valid;

import kr.co.platform.api.sign.dto.AuthenticationDto;
import kr.co.platform.api.sign.dto.JoinDto;
import kr.co.platform.api.sign.dto.LoginDto;
import kr.co.platform.api.sign.service.SignService;
import kr.co.platform.util.auth.CustomUserDetails;
import kr.co.platform.util.auth.JwtAuthProvider;
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

	/**
	 * @method 설명 : 회원가입
	 * @param joinDto
	 * @throws Exception
	 */
	@PostMapping(value = {"signup"})
	public ResponseEntity<AuthenticationDto> appJoin(
			@Valid @RequestBody JoinDto joinDto) throws Exception {

		AuthenticationDto authentication = apiSignService.joinService(joinDto);

		/**
		 * 회원가입 정상완료 후 해당 정보를 기반으로 로그인을 위해 인증토큰 발행
		 */
		CustomUserDetails user = new CustomUserDetails(
				authentication.getId(), 			// 회원 등록번호
				authentication.getEmail());			// 회원 아이디

		return ResponseEntity.ok()
				.header("x-access-token", jwtProvider
						.createToken(
								user.getUserPk(),
								user.getUsername(),
								user.getRoles()))
				.body(authentication);
	}
	
	/**
	 * @method 설명 : 로그인
	 * @param loginDto
	 * @return : 회원인증토큰, 회원정보
	 * @throws Exception
	 */
	@PostMapping(value = {"/signin"})
	public ResponseEntity<AuthenticationDto> appLogin(
			@Valid @RequestBody LoginDto loginDto) throws Exception {

		AuthenticationDto authentication = apiSignService.loginService(loginDto);
		/**
		 * 토큰발급을 위한 데이터는 UserDetails에서 담당
		 * 따라서 UserDetails를 세부 구현한 CustomUserDetails로 회원정보 전달
		 */
		CustomUserDetails user = new CustomUserDetails(
				authentication.getId(), 			// 회원 등록번호
				authentication.getEmail());			// 회원 아이디

		return ResponseEntity.ok()
				.header("x-access-token", jwtProvider
						.createToken(
								user.getUserPk(),
								user.getUsername(),
								user.getRoles()))
				.body(authentication);
	}
}
