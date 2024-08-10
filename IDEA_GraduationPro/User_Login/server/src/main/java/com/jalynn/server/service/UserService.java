package com.jalynn.server.service;

import com.jalynn.server.pojo.Login;

import java.util.List;

public interface UserService {

    /**
     * TODO 根据工号和密码获取用户信息
     */
    Login getByWorkIdAndEnPwd(String workId,String enPwd);

    /**
     * 根据用户工号查询用户信息
     */
    Login getByWorkId(String workId);

    /**
     * 保存（更新）用户信息（用在登陆时间）
     */
    Login updateLoginTime(Login login);

    /**
     * 获取登录信息
     */
    List<Login> getAll();
}
