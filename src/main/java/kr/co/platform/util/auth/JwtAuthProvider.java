package kr.co.platform.util.auth;

import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtAuthProvider {

    @Value("${spring.jwt.secret.signature}")
    private String signatureKey;

    @PostConstruct
    protected void init() {
        signatureKey = Base64.getEncoder().encodeToString(signatureKey.getBytes());
    }

    private final UserDetailsService userDetailsService;

    /**
     * @throws Exception
     * @method 설명 : jwt 토큰 발급
     */
    public String createToken(
            Long id,
            String username,
            String nickname) {
    	
    	/**
    	 * 토큰발급을 위한 데이터는 UserDetails에서 담당
    	 * 따라서 UserDetails를 세부 구현한 CustomUserDetails로 회원정보 전달
    	 */
    	CustomUserDetails user = new CustomUserDetails(
                id, 			// 번호
                username,		// 이메일
                nickname);	    // 닉네임
    	
    	// 유효기간설정을 위한 Date 객체 선언
    	Date date = new Date();
        
        final JwtBuilder builder = Jwts.builder()
                .setHeaderParam("typ", "JWT")                                                   // 토큰 타입
                .setSubject("x-access-token").setExpiration(new Date(date.getTime() + (1000L*60*60*12)))    // 토큰 유효시간 설정
                .claim("id", id)
                .claim("email", username)
                .claim("nickname", nickname)
                .claim("roles", user.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, signatureKey);                                           // 토큰 시그니쳐 설정

        return builder.compact();
    }

    // 토큰에서 회원 정보 추출
    public String getUserPk(String token) {
        return Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * @method 설명 : 컨텍스트에 해당 유저에 대한 권한을 전달하고 API 접근 전 접근 권한을 확인하여 접근 허용 또는 거부를 진행한다.
     */
    @SuppressWarnings("unchecked")
    public Authentication getAuthentication(String token) {

        // 토큰 기반으로 유저의 정보 파싱
        Claims claims = Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token).getBody();

        Long id = claims.get("id", Long.class);
        String email = claims.get("email", String.class);
        String nickname = claims.get("nickname", String.class);

        CustomUserDetails userDetails = new CustomUserDetails(id, email, nickname);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * @method 설명 : request객체 헤더에 담겨 있는 토큰 가져오기
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("x-access-token");
    }

    /**
     * @method 설명 : 토큰 유효시간 만료여부 검사 실행
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
