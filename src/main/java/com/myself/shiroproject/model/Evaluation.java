package com.myself.shiroproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation {
    private Integer evaluationId;

    private Integer evaluationPolicyId;

    private Integer evaluationRegistId;

    private String evaluationReport;

    private Date evaluationCreatTime;


}