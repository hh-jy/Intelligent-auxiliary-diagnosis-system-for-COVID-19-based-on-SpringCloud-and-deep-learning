package com.jalynn.server.controller;

import com.jalynn.common.LoginOutput;
import com.jalynn.server.VO.LoginVO;
import com.jalynn.server.VO.ResultVO;
import com.jalynn.server.constant.CookieConstant;
import com.jalynn.server.constant.RedisConstant;
import com.jalynn.server.enums.ResultEnum;
import com.jalynn.server.enums.UserRoleEnum;
import com.jalynn.server.pojo.Login;
import com.jalynn.server.service.UserService;
import com.jalynn.server.utils.CookieUtil;
import com.jalynn.server.utils.ResultVOUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登陆
     */
    @PostMapping("/login")
    public ResultVO login(@RequestParam("workId") String workId,
                          @RequestParam("pwd") String pwd,
                          HttpServletResponse response,
                          HttpServletRequest request){
        //判断是否已经登陆
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if ((cookie !=null) &&
        !StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,
                cookie.getValue())))
               /* workId.equals(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,
                        cookie.getValue())))*/
        ){
            return ResultVOUtil.success();
        }

        //1.根据工号和密码查找数据库内容
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Login login = userService.getByWorkId(workId);
        if (login == null){
            return ResultVOUtil.error(ResultEnum.USER_NULL);
        }
        login.setLoginTime(new Date(System.currentTimeMillis()));
        userService.updateLoginTime(login);

        //2.数据库信息进行封装拷贝
        LoginVO loginVO = new LoginVO();
        BeanUtils.copyProperties(login, loginVO);

        String enPwd = loginVO.getEnPwd();
        if (!bCryptPasswordEncoder.matches(pwd,enPwd)){
            return ResultVOUtil.error(ResultEnum.LOGIN_ERROR);
        }

        ////3.判断角色
        if (loginVO.getRoleId().equals(UserRoleEnum.DOCTOR.getCode())){
            //3.1 医生：cookie设置authorization=doctor（roleID）
            CookieUtil.set(response,CookieConstant.AUTH,UserRoleEnum.DOCTOR.getMessage(),CookieConstant.expire);
        }
        if (loginVO.getRoleId().equals(UserRoleEnum.ADMIN.getCode())){
            //3.2 管理员：cookie设置authorization=admin（roleID）
            CookieUtil.set(response,CookieConstant.AUTH,UserRoleEnum.ADMIN.getMessage(),CookieConstant.expire);
        }

        //4.redis设置key=UUID，value=workId
        String token = UUID.randomUUID().toString();
        Integer expire = CookieConstant.expire;
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_TEMPLATE,token),
                loginVO.getWorkId(),
                expire,
                TimeUnit.SECONDS
                );

        //5.设置cookie里面的token=UUID
        CookieUtil.set(response,CookieConstant.TOKEN,token,CookieConstant.expire);

        return ResultVOUtil.success();
    }

    /**
     * 注销/退出登陆
     */
    @PostMapping("/logout")
    public ResultVO logout(@RequestParam("workId") String workId,HttpServletRequest request,
                           HttpServletResponse response){
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if ((cookie ==null) ||
                StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE,
                        cookie.getValue())))
        ){
            return ResultVOUtil.error(ResultEnum.LOGOUT_ERROR);
        }
        String token = String.format(RedisConstant.TOKEN_TEMPLATE,cookie.getValue());
        stringRedisTemplate.delete(token);
        cookie.setMaxAge(0);//立马删除型
        response.addCookie(cookie);//重新写入，覆盖之前
        System.out.println(cookie.getValue());
        return ResultVOUtil.success();
    }

    /**
     * 获取指定的登录信息(给管理模块调用)
     */
    @GetMapping("/getAllForManage")
    public List<LoginOutput> getAll(){
        List<Login> list = userService.getAll();
        List<LoginOutput> logins = new ArrayList<>();
        for (Login login : list) {
            LoginOutput loginOutput = new LoginOutput();
            BeanUtils.copyProperties(login,loginOutput);
            logins.add(loginOutput);
        }
        return logins;
    }

}
