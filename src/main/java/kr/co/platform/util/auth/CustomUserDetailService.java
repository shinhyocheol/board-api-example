package kr.co.platform.util.auth;

import kr.co.platform.api.member.domain.entity.Members;
import kr.co.platform.api.member.domain.repository.MemberRepository;
import kr.co.platform.exception.custom.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Members entity = memberRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("존재하지 않는 회원의 정보입니다."));

        UserDetails member = new CustomUserDetails(entity.getId(), email, "USER");

        return member;
    }

}
