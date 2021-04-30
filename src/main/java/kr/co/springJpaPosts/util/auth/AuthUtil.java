package kr.co.springJpaPosts.util.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class AuthUtil {

    public static boolean hasRole(String role) {
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
                .getAuthentication().getAuthorities();
        boolean hasRole = false;
        for (GrantedAuthority authority : authorities) {
            hasRole = authority.getAuthority().equals(role);
            if (hasRole) {
                break;
            }
        }
        return hasRole;
    }

    public static String getRole() {
        @SuppressWarnings("unchecked")
        Collection<GrantedAuthority> authorities =
                (Collection<GrantedAuthority>) SecurityContextHolder.getContext()
                        .getAuthentication()
                        .getAuthorities();
        String role = "";
        for (GrantedAuthority authority : authorities) {
            role = authority.getAuthority().replace("ROLE_", "");
        }
        return role;
    }

    public static String getClientUUID() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getClientUUID();
    }

    public static long getClientRegno() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getClientPk();
    }

    public static String getMemberId() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }

    public static long getMemberRegno() {
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUserPk();
    }

}
