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

    private long clientPk;
    private String clientUUID;

    private long userPk;
    private String username;
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public CustomUserDetails(
            long userPk,
            String username) {
        this.userPk = userPk;
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        List<SimpleGrantedAuthority> roles = this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        roles.add(new SimpleGrantedAuthority("ROLE_USER"));

        return roles;

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
