package com.jalynn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**/
@SpringBootApplication
@EnableDiscoveryClient
//@ComponentScan(basePackages = "com.jalynn")
public class UserLoginServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserLoginServiceApplication.class, args);
    }

}
