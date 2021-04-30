package kr.co.springJpaPosts.api.sign.service;

import kr.co.springJpaPosts.api.member.domain.entity.Members;
import kr.co.springJpaPosts.api.member.domain.repository.MemberRepository;
import kr.co.springJpaPosts.api.sign.dto.AuthenticationDto;
import kr.co.springJpaPosts.api.sign.dto.JoinDto;
import kr.co.springJpaPosts.api.sign.dto.LoginDto;
import kr.co.springJpaPosts.util.advice.exception.DuplicatedException;
import kr.co.springJpaPosts.util.advice.exception.ForbiddenException;
import kr.co.springJpaPosts.util.advice.exception.UserNotFoundException;
import kr.co.springJpaPosts.util.empty.Empty;
import kr.co.springJpaPosts.util.object.ObjectUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("apiSignService")
@AllArgsConstructor
public class SignService {

	private static final SecureRandom secureRandom = new SecureRandom(); //threadsafe

	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); //threadsafe

	private MemberRepository memberRepository;

	private BCryptPasswordEncoder passwordEncoder;
	
	private ObjectUtil objUtil;

	public void joinService(JoinDto joinDto) {

		// 아이디 중복체크
		if (!Empty.validation(memberRepository.countByEmail(joinDto.getEmail())))
			throw new DuplicatedException("Duplicated Member");


	}


	public AuthenticationDto loginService(LoginDto loginDto) {

		// 회원 엔티티 객체 생성 및 조회시작

		Members member = memberRepository.findByEmail(loginDto.getEmail())
				.orElseThrow(() -> new UserNotFoundException("User Not Found"));

		if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword()))
			throw new ForbiddenException("Passwords do not match");

		// 회원정보를 인증클래스 객체(authentication)로 매핑
		AuthenticationDto authentication = objUtil.toDto(member, AuthenticationDto.class);


		return authentication;
	}

}
