package kr.co.platform.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/** JPA Auditing 기능을 사용하기 위해 해당 어노테이션을 붙여줌. */
@EnableJpaAuditing
@EnableTransactionManagement
@Configuration
@Slf4j
public class DataBaseConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.master")
    public DataSource masterDataSource() {

        log.debug("Create Master Datasource Bean!!");

        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public PlatformTransactionManager transactionManager(
            @Qualifier (value = "masterDataSource") DataSource masterDataSource) {

        log.debug("Create Transaction Manager Bean!!");

        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setDataSource(masterDataSource);

        return jpaTransactionManager;
    }

}

