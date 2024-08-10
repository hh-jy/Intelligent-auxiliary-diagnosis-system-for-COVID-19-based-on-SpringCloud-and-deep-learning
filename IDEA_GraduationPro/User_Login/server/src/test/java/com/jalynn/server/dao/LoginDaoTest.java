package com.jalynn.server.dao;

import com.jalynn.server.pojo.Login;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class LoginDaoTest {

    @Autowired
    LoginDao loginDao;

    @Test
    void findAll(){
        for (Login login : loginDao.findAll()) {
            System.out.println(login);
        }
    }
}