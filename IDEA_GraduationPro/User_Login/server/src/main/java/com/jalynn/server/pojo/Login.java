package com.jalynn.server.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "login")
public class Login {

    @Id
    private String workId;//用户工号

    private String enPwd;//加密密码

    private Integer roleId;//角色Id（1医生/2管理员）

    private Date createTime;//创建时间

    private Date correctTime;//修改时间

    private Date loginTime;//登陆时间

}
