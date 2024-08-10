package com.jalynn.server.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosisResultVO {

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
     * 检查日期
     */
    private Date diagDate;

}
