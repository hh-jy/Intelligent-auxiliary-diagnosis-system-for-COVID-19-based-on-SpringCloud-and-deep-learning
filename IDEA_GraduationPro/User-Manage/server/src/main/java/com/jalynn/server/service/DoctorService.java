package com.jalynn.server.service;

import com.jalynn.server.pojo.Doctor;

import java.util.List;

public interface DoctorService {
    List<Doctor> getAll();

    Doctor getById(String workId);

    Doctor save(Doctor doctor);
}
