package kr.co.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@EnableJpaAuditing /** JPA Auditing 기능을 사용하기 위해 해당 어노테이션을 붙여줌. */
@SpringBootApplication
public class SpringJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaApplication.class, args);
	}
	
	@Bean
	public HiddenHttpMethodFilter HiddenHttpMethodFilter() {
		return new HiddenHttpMethodFilter();
	}
	
}
