package com.jalynn.server.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DoctorDeptResultVO {

    private String workId;

    @JsonProperty("name")
    private String doctorName;

    @JsonProperty("gender")
    private Integer doctorGender;

    private DeptResultVO deptResultVO;

    @JsonProperty("tel")
    private String doctorTel;

    private Date createTime;

    private Date correctTime;
}
