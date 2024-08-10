package com.jalynn.server.service.impl;

import com.jalynn.server.dao.DeptDao;
import com.jalynn.server.pojo.Dept;
import com.jalynn.server.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptDao;

    @Override
    public List<Dept> getAll() {
        return deptDao.findAll();
    }

    @Override
    public Dept saveDept(Dept dept) {
        return deptDao.save(dept);
    }

    @Override
    public void deleteDeptById(Integer deptId) {
        deptDao.deleteByDeptId(deptId);
    }

    @Override
    public Dept getById(Integer deptId) {
        return deptDao.findByDeptId(deptId);
    }

    @Override
    public Dept getByName(String deptName) {
        return deptDao.findByDeptName(deptName);
    }
}
