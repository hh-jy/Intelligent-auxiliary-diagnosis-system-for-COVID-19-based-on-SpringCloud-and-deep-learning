package com.jalynn.server.dao;

import com.jalynn.server.pojo.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ManagerDao extends JpaRepository<Manager,String> {
    Manager findByWorkId(String workId);

    List<Manager> findByWorkIdIn(List<String> workIds);
}
