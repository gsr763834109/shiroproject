package com.myself.shiroproject.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registrant {

    private Integer registrantId;

    private String registrantName;

    private String registrantAge;

    private String registrantSex;

    private String registrantEducation;

    private String registrantPhone;

    private String registrantPwd;

    private Integer registrantHospital;

    private Integer registrantLock;

    private Integer registrantPlanTrain;

    private Integer registrantPlanAll;

    private Date registrantModifyTime;

    private Date registrantCutTime;

    private Integer registrantGold;

    private Date registrantTrainTime;

    private Date registrantCreatTime;

    private String registrantPlan;

    private List<EvaluationWithBLOBs> evaluations;


}