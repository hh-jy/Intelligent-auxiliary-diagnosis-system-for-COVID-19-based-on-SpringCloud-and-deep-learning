package com.jalynn.server.service.impl;

import com.jalynn.server.dao.LoginDao;
import com.jalynn.server.enums.UserRoleEnum;
import com.jalynn.server.pojo.Login;
import com.jalynn.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    LoginDao loginDao;

    @Override
    public Login getByWorkIdAndEnPwd(String workId, String enPwd) {
        return loginDao.findByWorkIdAndEnPwd(workId,enPwd);
    }

    @Override
    public Login getByWorkId(String workId) {
        return loginDao.findByWorkId(workId);
    }

    @Override
    public Login updateLoginTime(Login login) {
        return loginDao.save(login);
    }

    @Override
    public List<Login> getAll() {
        return loginDao.findAll();
    }

}
