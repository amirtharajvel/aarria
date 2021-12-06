package com.aarria.retail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aarria.retail"})
@EnableJpaRepositories(basePackages = {"com.aarria.retail.persistence.repository", "com.aarria.retail.core.domain"})
public class RetailApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder
                                                         application) {
        return application.sources(RetailApplication.class);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RetailApplication.class, args);
    }

}
