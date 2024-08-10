package com.jalynn.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginOutput {
    private String workId;

    private String enPwd;

    private Integer roleId;

    private Date createTime;

    private Date correctTime;

    private Date loginTime;
}
