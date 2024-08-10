package com.jalynn.server.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictResultVO {
    /**
     * 预测类型（1新冠/2普通/3正常/4阴影）
     */
    private String diagType;

    /**
     * 诊断结果（1阴性/2阳性）
     */
    private String diagResult;
}
