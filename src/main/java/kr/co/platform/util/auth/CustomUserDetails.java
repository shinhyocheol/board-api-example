package kr.co.platform.util.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("serial")
@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private long id;

    private String username;

    private String password;

    private List<String> roles = new ArrayList<>();

    public CustomUserDetails(
            long id,
            String email,
            String role) {

        this.id = id;
        this.username = email;

        List<String> roleList = new ArrayList<String>();
        roleList.add("ROLE_" + role);

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> roles = this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return roles;

    }

    // 계정 만료여부
    @Override
    public boolean isAccountNonExpired() {
        // true : 만료되지 않음
        // false : 만료됨
        return true;
    }

    // 계정 잠김 여부
    @Override
    public boolean isAccountNonLocked() {
        // true : 잠기지 않음
        // false : 잠김
        return true;
    }

    // 비밀번호 잠김 여부
    @Override
    public boolean isCredentialsNonExpired() {
        // true : 잠기지 않음
        // false : 잠김
        return true;
    }

    // 계정 활성화 여부
    @Override
    public boolean isEnabled() {
        // true : 활성화 상태
        // false : 비활성화 상태
        return true;
    }
}
