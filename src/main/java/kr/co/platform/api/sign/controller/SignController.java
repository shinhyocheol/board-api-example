package kr.co.platform.api.sign.controller;

import javax.validation.Valid;

import kr.co.platform.api.sign.dto.AuthenticationDto;
import kr.co.platform.api.sign.dto.JoinDto;
import kr.co.platform.api.sign.dto.LoginDto;
import kr.co.platform.api.sign.service.SignService;
import kr.co.platform.util.auth.AuthProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = {""}, produces = MediaType.APPLICATION_JSON_VALUE)
public class SignController {

	private final SignService apiSignService;
	
	private final AuthProvider jwtProvider;

	/**
	 * @method 설명 : 회원가입
	 * @param joinDto
	 * @throws Exception
	 */
	@PostMapping(value = {"signup"})
	public ResponseEntity<Boolean> appJoin(
			@Valid @RequestBody JoinDto joinDto) throws Exception {

		return ResponseEntity.ok()
				.body(apiSignService.regMember(joinDto));
	}
	
	/**
	 * @method 설명 : 로그인
	 * @param loginDto
	 * @throws Exception
	 */
	@PostMapping(value = {"/signin"})
	public ResponseEntity<AuthenticationDto> appLogin(
			@Valid @RequestBody LoginDto loginDto) throws Exception {

		AuthenticationDto authentication = apiSignService.loginMember(loginDto);

		return ResponseEntity.ok()
				.header("x-access-token", jwtProvider
						.createToken(
							authentication.getId(),
							authentication.getEmail(),
							"USER"))
				.body(authentication);
	}
}
