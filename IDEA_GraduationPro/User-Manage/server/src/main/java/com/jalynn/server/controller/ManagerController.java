package com.jalynn.server.controller;

import com.jalynn.clientfeign.LoginClient;
import com.jalynn.common.LoginOutput;
import com.jalynn.server.VO.ManagerLoginResultVO;
import com.jalynn.server.VO.ManagerResultVO;
import com.jalynn.server.VO.ResultVO;
import com.jalynn.server.enums.ResultEnum;
import com.jalynn.server.pojo.Manager;
import com.jalynn.server.service.ManagerService;
import com.jalynn.server.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class ManagerController {

    @Autowired
    ManagerService managerService;

    @Resource
    LoginClient loginClient;

    /**
     * 获取所有关于管理员的信息 [基本信息]
     */
    @GetMapping("/getAll")
    public ResultVO getAll(){
        List<Manager> list = managerService.getAll();
        List<ManagerResultVO> managerResultVOS = new ArrayList<>();
        for (Manager manager : list) {
            ManagerResultVO vo = new ManagerResultVO();
            BeanUtils.copyProperties(manager,vo);
            managerResultVOS.add(vo);
        }
        return ResultVOUtil.success(managerResultVOS);
    }


    /**
     * 根据用户工号查找管理员信息 [基本信息]
     */
    @GetMapping("/manById/{workId}")
    public ResultVO getById(@PathVariable("workId") String workId){
        Manager manager = managerService.getById(workId);
        ManagerResultVO vo = new ManagerResultVO();
        BeanUtils.copyProperties(manager,vo);
        return ResultVOUtil.success(vo);
    }

    /**
     * TODO 修改管理员的基本信息
     */
    @PutMapping("/udpateMan")
    public ResultVO updateMan(@RequestBody Manager manager){
        Manager byId = managerService.getById(manager.getWorkId());
        if (byId == null){
            return ResultVOUtil.error(ResultEnum.UPDATE_ERROR);
        }
        managerService.save(manager);
        return ResultVOUtil.success();
    }

    /**
     * TODO 添加管理员信息 调
     * 1.注册登录信息
     * 2.填写管理员基本信息
     */

    /**
     * TODO 根据用户编号删除管理员信息（包括登陆信息） 调
     */


    /**
     * TODO 获取所有关于管理员的登陆信息 （工号，姓名，登陆信息） 调
     * */

    @GetMapping("/loginAll")
    public ResultVO getLoginAll(){
        List<Manager> all = managerService.getAll();
        List<ManagerLoginResultVO> vos = new ArrayList<>();
        for (Manager manager : all) {
            ManagerLoginResultVO vo = new ManagerLoginResultVO();
            BeanUtils.copyProperties(manager,vo);
            vos.add(vo);
        }
        List<String> allIds = new ArrayList<>();
        for (Manager manager : all) {
            String workId = manager.getWorkId();
            allIds.add(workId);
        }
        List<LoginOutput> loginAll = loginClient.getAll();
        List<ManagerLoginResultVO> collect = vos.stream().map(m -> {
            loginAll.stream().filter(m2 -> Objects.equals(m.getWorkId(), m2.getWorkId())).forEach(m2 -> {
                m.setEnPwd(m2.getEnPwd());
                m.setRoleId(m2.getRoleId());
            });
            return m;
        }).collect(Collectors.toList());

        return ResultVOUtil.success(collect);

    }

    /**
     * TODO 根据用户工号查找管理员登陆信息 （工号，姓名，登陆信息） 调
     */

    /**
     * TODO 修改管理员的登录信息 调
     */

}
