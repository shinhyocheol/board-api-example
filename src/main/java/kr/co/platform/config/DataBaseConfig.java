package kr.co.platform.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;

/** JPA Auditing 기능을 사용하기 위해 해당 어노테이션을 붙여줌. */
@EnableJpaAuditing
@Configuration
public class DataBaseConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }

}

