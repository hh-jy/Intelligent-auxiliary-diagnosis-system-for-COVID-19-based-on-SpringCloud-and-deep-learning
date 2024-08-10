package com.jalynn.server.dao;

import com.jalynn.server.pojo.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DeptDao extends JpaRepository<Dept,Integer> {
    void deleteByDeptId(Integer deptId);

    Dept findByDeptId(Integer deptId);

    Dept findByDeptName(String deptName);
}
