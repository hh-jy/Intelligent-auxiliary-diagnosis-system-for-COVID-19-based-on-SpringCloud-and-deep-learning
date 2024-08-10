package com.jalynn.server.controller;

import com.jalynn.server.VO.DeptResultVO;
import com.jalynn.server.VO.DoctorDeptResultVO;
import com.jalynn.server.VO.ResultVO;
import com.jalynn.server.enums.ResultEnum;
import com.jalynn.server.pojo.Dept;
import com.jalynn.server.pojo.Doctor;
import com.jalynn.server.service.DeptService;
import com.jalynn.server.service.DoctorService;
import com.jalynn.server.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @Autowired
    DeptService deptService;

    /**
     * 获取所有关于医生的信息（基本信息，科室） [基本信息管理]
     */
    @GetMapping("/manAll")
    public ResultVO getManAll(){
        List<Doctor> doctorList = doctorService.getAll();
        List<DoctorDeptResultVO> list = new ArrayList<>();
        for (Doctor doctor : doctorList) {
            Integer deptId = doctor.getDeptId();
            Dept byId = deptService.getById(deptId);
            DeptResultVO deptResultVO = new DeptResultVO();
            BeanUtils.copyProperties(byId,deptResultVO);
            DoctorDeptResultVO doctorDeptResultVO = new DoctorDeptResultVO();
            BeanUtils.copyProperties(doctor,doctorDeptResultVO);
            doctorDeptResultVO.setDeptResultVO(deptResultVO);
            list.add(doctorDeptResultVO);
        }
        return ResultVOUtil.success(list);
    }

    /**
     * 根据工号查询医生的基本信息 [基本信息管理]
     */
    @GetMapping("/manById/{workId}")
    public ResultVO getManById(@PathVariable("workId")String workId){
        Doctor doctor = doctorService.getById(workId);
        System.out.println(doctor);
        Dept dept = deptService.getById(doctor.getDeptId());
        System.out.println(dept);
        DeptResultVO deptResultVO = new DeptResultVO();
        BeanUtils.copyProperties(dept,deptResultVO);
        DoctorDeptResultVO doctorDeptResultVO = new DoctorDeptResultVO();
        BeanUtils.copyProperties(doctor,doctorDeptResultVO);
        doctorDeptResultVO.setDeptResultVO(deptResultVO);
        return ResultVOUtil.success(doctorDeptResultVO);
    }

    /**
     * 修改关于医生的信息 [基本信息管理]
     */
    @PutMapping("/manUpdate")
    public ResultVO updateMan(@RequestBody Doctor doctor){
        Doctor byId = doctorService.getById(doctor.getWorkId());
        if (byId == null){
            return ResultVOUtil.error(ResultEnum.UPDATE_ERROR);
        }
        doctorService.save(doctor);
        return ResultVOUtil.success();
    }

    /**
     * TODO 添加关于医生的信息 调
     * 1. 注册登陆信息
     * 2. 填写基本信息
     * 3. 选择科室信息
     */

    /**
     * TODO 根据工号删除医生的相关信息（包括登陆信息） 调
     */

    /**
     * TODO 获取所有医生的登录信息 (工号，姓名，登录信息) 调
     */

    /**
     * TODO 根据工号查询医生的登陆信息 (工号，姓名，登录信息) 调
     */

    /**
     * TODO 修改关于医生的登陆信息
     */


}
