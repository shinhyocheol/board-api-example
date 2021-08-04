package kr.co.platform.api.member.service;

import org.springframework.stereotype.Service;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.member.domain.repository.MemberRepository;
import kr.co.platform.api.member.dto.MemberResultDto;
import kr.co.platform.model.CustomModelMapper;
import kr.co.platform.util.advice.exception.ApiOtherException;
import lombok.RequiredArgsConstructor;

@Service("memberService")
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final CustomModelMapper modelMapper;

	public MemberResultDto getMemberByIdService(Long id) {
		
		Members entity = memberRepository.findById(id)
				.orElseThrow(() -> new ApiOtherException("Member Not Found"));
		
		return modelMapper.toMapping(entity, MemberResultDto.class);
	}
	
	

}
