package com.jalynn.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.jalynn.clientfeign","com.jalynn.server"})
//@ComponentScan("com.jalynn.server")
//@EnableJpaRepositories(basePackag；es = "com.jalynn.server.dao")//扫描 @Repository 注解
//@EntityScan(basePackages = "com.jalynn.server.pojo")//扫描 @Entity 注解
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.jalynn.clientfeign")
public class UserManageServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserManageServerApplication.class, args);
    }

}
