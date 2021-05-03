package kr.co.springJpaPosts.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class SpringServletConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
