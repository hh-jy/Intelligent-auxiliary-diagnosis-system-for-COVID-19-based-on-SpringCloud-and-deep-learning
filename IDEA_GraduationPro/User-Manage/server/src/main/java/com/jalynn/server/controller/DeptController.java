package com.jalynn.server.controller;

import com.jalynn.server.VO.DeptResultVO;
import com.jalynn.server.VO.ResultVO;
import com.jalynn.server.enums.ResultEnum;
import com.jalynn.server.pojo.Dept;
import com.jalynn.server.service.DeptService;
import com.jalynn.server.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.ws.rs.PUT;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Resource
    private DeptService deptService;

    /**
     * 获取所有科室的信息
     */
    @GetMapping("/getAll")
    public ResultVO getAllDept(){
        List<Dept> list = deptService.getAll();
        List<DeptResultVO> deptResultVOS = new ArrayList<>();
        for (Dept dept : list) {
            DeptResultVO deptResultVO = new DeptResultVO();
            BeanUtils.copyProperties(dept,deptResultVO);
            deptResultVOS.add(deptResultVO);
        }
        return ResultVOUtil.success(deptResultVOS);
    }

    /**
     * 添加科室信息
     */
    @PutMapping("/add")
    public ResultVO addDept(@RequestBody Dept dept){
        Dept dept1 = deptService.getById(dept.getDeptId());
        if (dept1 != null){
            return ResultVOUtil.error(ResultEnum.INSERT_ERROR);
        }
        deptService.saveDept(dept);
        return ResultVOUtil.success();
    }

    /**
     * 根据科室名称查找科室信息
     */
    @GetMapping("/getByName/{deptName}")
    public ResultVO getByName(@PathVariable("deptName") String deptName){
        Dept dept = deptService.getByName(deptName);
        if (dept ==null){
            return ResultVOUtil.error(ResultEnum.FIND_ERROR);
        }
        return ResultVOUtil.success(dept);
    }

    /**
     * 根据科室编号查找科室信息
     */
    @GetMapping("/getById/{deptId}")
    public ResultVO getById(@PathVariable("deptId") Integer deptId){
        Dept dept = deptService.getById(deptId);
        if (dept ==null){
            return ResultVOUtil.error(ResultEnum.FIND_ERROR);
        }
        return ResultVOUtil.success(dept);
    }

    /**
     * 根据科室编号删除科室信息
     */
    @DeleteMapping("/delete")
    public ResultVO delete(Integer deptId){
        deptService.deleteDeptById(deptId);
        return ResultVOUtil.success();
    }

    /**
     * 修改科室信息
     */
    @PutMapping("/update")
    public ResultVO update(@RequestBody Dept dept){
        Dept dept1 = deptService.getById(dept.getDeptId());
        if (dept1 == null){
            return ResultVOUtil.error(ResultEnum.UPDATE_ERROR);
        }
        deptService.saveDept(dept);
        return ResultVOUtil.success();
    }
}
