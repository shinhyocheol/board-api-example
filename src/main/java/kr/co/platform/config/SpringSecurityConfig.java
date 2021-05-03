package kr.co.platform.config;

import kr.co.platform.util.auth.CustomAccessDeniedPoint;
import kr.co.platform.util.auth.CustomAuthenticationEntryPoint;
import kr.co.platform.util.auth.JwtAuthProvider;
import kr.co.platform.util.auth.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{

	private final JwtAuthProvider jwtAuthProvider;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	} 

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	} 
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		
		CorsConfiguration configuration = new CorsConfiguration();

		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		configuration.setMaxAge((long) 3600);
		configuration.setAllowCredentials(false);
		configuration.addExposedHeader("x-access-token");
		configuration.addExposedHeader("content-disposition");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		
		return source;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		
		web.ignoring().antMatchers(
				"/static/css/**", 
				"/static/js/**", 
				"/static/img/**", 
				"/static/**"
				);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.httpBasic().disable()
				.csrf().disable()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.authorizeRequests()
				.requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
					.antMatchers("/signup").permitAll()			// 회원가입
					.antMatchers("/signin/**").permitAll() 		// 로그인
					.antMatchers("/exception/**").permitAll() 	// 예외처리 포인트
					.anyRequest().permitAll()
					//.anyRequest().hasRole("USER")							// 이외 나머지는 권한 필요하지만 픽폰에서 권한레벨은 사용되지 않고 관리자도 분리되어 있으므로 USER 권한을 부여하였음
				.and()
			.cors()
				.and()
			.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedPoint())
				.and()
			.exceptionHandling().authenticationEntryPoint(new CustomAuthenticationEntryPoint())
				.and()
			.addFilterBefore(new JwtAuthenticationFilter(jwtAuthProvider), UsernamePasswordAuthenticationFilter.class);
	}
	
}
