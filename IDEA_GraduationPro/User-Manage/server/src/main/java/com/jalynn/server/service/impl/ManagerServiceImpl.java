package com.jalynn.server.service.impl;

import com.jalynn.server.dao.ManagerDao;
import com.jalynn.server.pojo.Manager;
import com.jalynn.server.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    @Autowired
    ManagerDao managerDao;

    @Override
    public List<Manager> getAll() {
        return managerDao.findAll();
    }

    @Override
    public Manager getById(String workId) {
        return managerDao.findByWorkId(workId);
    }

    @Override
    public Manager save(Manager manager) {
        return managerDao.save(manager);
    }

    @Override
    public List<Manager> getInId(List<String> workIds) {
        return managerDao.findByWorkIdIn(workIds);
    }
}
