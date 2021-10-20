package kr.co.platform.api.member.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.member.domain.repository.MemberRepository;
import kr.co.platform.api.member.dto.MemberResultDto;
import kr.co.platform.exception.custom.ApiOtherException;
import lombok.RequiredArgsConstructor;

@Service("memberService")
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	private final ModelMapper modelMapper;

	public MemberResultDto getMemberById(Long id) {
		
		Members entity = memberRepository.findById(id)
				.orElseThrow(() -> new ApiOtherException("Member Not Found"));

		return modelMapper.map(entity, MemberResultDto.class);
	}
	
	

}
