package com.jalynn.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Manager {

    /**
     * 用户工号
     */
    @Id
    private String workId;

    /**
     * 姓名
     */
    private String adminName;

    /**
     * 性别（1女/2男）
     */
    private Integer adminGender;

    /**
     * 联系电话
     */
    private String adminTel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date correctTime;
}
