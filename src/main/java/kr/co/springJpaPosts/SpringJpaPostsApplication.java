package kr.co.springJpaPosts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing /** JPA Auditing 기능을 사용하기 위해 해당 어노테이션을 붙여줌. */
@SpringBootApplication
public class SpringJpaPostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJpaPostsApplication.class, args);
	}

}
