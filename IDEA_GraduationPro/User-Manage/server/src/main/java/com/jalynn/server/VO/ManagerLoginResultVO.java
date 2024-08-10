package com.jalynn.server.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerLoginResultVO {
    private String workId;

    @JsonProperty("name")
    private String adminName;

    @JsonProperty("gender")
    private Integer adminGender;

    @JsonProperty("tel")
    private String adminTel;

    private Date createTime;

    private Date correctTime;

    private String enPwd;

    private Integer roleId;
}
