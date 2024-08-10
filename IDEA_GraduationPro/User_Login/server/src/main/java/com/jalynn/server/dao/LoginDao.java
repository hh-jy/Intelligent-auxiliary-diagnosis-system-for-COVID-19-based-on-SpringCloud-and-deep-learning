package com.jalynn.server.dao;

import com.jalynn.server.pojo.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoginDao extends JpaRepository<Login,String> {

    List<Login> findByRoleId(Integer roleId);

    Login findByWorkIdAndEnPwd(String workId,String enPwd);

    Login findByWorkId(String workId);

    Login save(Login login);

    List<Login> findByWorkIdIn(List<String> workIds);

}
