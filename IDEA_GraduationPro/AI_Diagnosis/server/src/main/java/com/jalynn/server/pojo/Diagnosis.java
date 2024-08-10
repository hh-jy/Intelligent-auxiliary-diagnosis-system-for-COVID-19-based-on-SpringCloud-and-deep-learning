package com.jalynn.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Diagnosis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * 影像诊断编号
     */
    private Integer imgDiagId;

    /**
     * 患者编号
     */
    private String patientId;

    /**
     * 影像部位
     */
    private String imgPart;

    /**
     * 预测类型（1新冠/2普通/3正常/4阴影）
     */
    private Integer diagType;

    /**
     * 诊断结果（1阴性/2阳性）
     */
    private Integer diagResult;

    /**
     * 影像路径
     */
    private String imgUrl;

    /**
     * 检查日期
     */
    private Date diagDate;

    /**
     * 报告编号
     */
    private Integer repoId;

    /**
     * 状态（1已阅，2未阅）
     */
    private Integer status;
}
