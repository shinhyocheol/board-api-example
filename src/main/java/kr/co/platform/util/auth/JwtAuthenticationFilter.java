package kr.co.platform.util.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private JwtAuthProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtAuthProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpReq = (HttpServletRequest)request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        try {
            if("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
                httpRes.setStatus(HttpServletResponse.SC_OK);
            } else {

                String token = jwtTokenProvider.resolveToken(httpReq);
                if (token != null) {
                    if(jwtTokenProvider.validateToken(token)) {

                        /** 사용자 인증토큰 검사 */
                        Authentication auth = jwtTokenProvider.getAuthentication(token);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                    }
                }
                filterChain.doFilter(request, response);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}