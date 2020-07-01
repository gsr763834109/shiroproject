package com.myself.shiroproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationWithBLOBs extends Evaluation {
    private String evaluationAnswer;

    private String evaluationAnalysis;

    private String evaluationScore;

    private String evaluationResult;

}