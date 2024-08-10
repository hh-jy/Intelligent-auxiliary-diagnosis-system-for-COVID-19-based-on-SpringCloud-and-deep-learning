package com.jalynn.server.service;

import com.jalynn.server.pojo.Manager;

import java.util.List;

public interface ManagerService {
    List<Manager> getAll();

    Manager getById(String workId);

    Manager save(Manager manager);

    List<Manager> getInId(List<String> workIds);
}
