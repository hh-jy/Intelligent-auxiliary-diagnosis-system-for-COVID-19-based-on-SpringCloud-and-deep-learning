package com.jalynn.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Dept {

    /**
     * 科室编号
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer deptId;

    /**
     * 科室名称
     */
    private String deptName;

    /**
     * 科室状态（1就诊中/2停诊）
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;
}
