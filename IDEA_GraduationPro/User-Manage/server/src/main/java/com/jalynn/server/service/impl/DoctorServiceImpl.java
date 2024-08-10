package com.jalynn.server.service.impl;

import com.jalynn.server.dao.DoctorDao;
import com.jalynn.server.pojo.Doctor;
import com.jalynn.server.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorDao doctorDao;

    @Override
    public List<Doctor> getAll() {
        return doctorDao.findAll();
    }

    @Override
    public Doctor getById(String workId) {
        return doctorDao.findByWorkId(workId);
    }

    @Override
    public Doctor save(Doctor doctor) {
        return doctorDao.save(doctor);
    }
}
