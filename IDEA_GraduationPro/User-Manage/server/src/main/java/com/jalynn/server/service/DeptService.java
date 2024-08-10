package com.jalynn.server.service;

import com.jalynn.server.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 获取所有科室信息
     */
    List<Dept> getAll();

    /**
     * 修改/新增 科室信息
     */
    Dept saveDept(Dept dept);


    /**
     * 根据编号删除科室信息
     */
    void deleteDeptById(Integer deptId);

    /**
     * 根据科室编号查询科室信息
     */
    Dept getById(Integer deptId);

    Dept getByName(String deptName);
}
