package com.jalynn.server.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeptResultVO {

    @JsonProperty("name")
    private String deptName;

    private Integer status;

    private Date createTime;
}
