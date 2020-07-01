package com.myself.shiroproject.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName Role
 * @Description
 * @Author vici
 * @Date 2020/3/23 17:56
 * @Version V2.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "role")
public class Role  implements Serializable{
    @TableId(value = "role_id",type = IdType.AUTO)
    private String roleId;
    @TableField("role_name")
    private String roleName;
}
