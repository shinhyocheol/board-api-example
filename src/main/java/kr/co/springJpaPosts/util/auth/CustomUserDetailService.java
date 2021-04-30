package kr.co.springJpaPosts.util.auth;

import kr.co.springJpaPosts.api.member.domain.entity.Members;
import kr.co.springJpaPosts.api.member.domain.repository.MemberRepository;
import kr.co.springJpaPosts.util.advice.exception.UserNotFoundException;
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
                .orElseThrow(() -> new UserNotFoundException("There is no result data"));

        UserDetails member = new CustomUserDetails(entity.getId(), email);

        return member;
    }

}
