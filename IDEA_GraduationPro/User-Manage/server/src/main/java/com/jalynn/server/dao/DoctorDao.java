package com.jalynn.server.dao;

import com.jalynn.server.pojo.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface DoctorDao extends JpaRepository<Doctor,String> {
    Doctor findByWorkId(String workId);
}
