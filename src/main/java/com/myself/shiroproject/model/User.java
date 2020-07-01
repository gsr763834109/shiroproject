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
 * @ClassName User
 * @Description
 * @Author vici
 * @Date 2020/3/23 17:55
 * @Version V2.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "user")
public class User implements Serializable {
    @TableId(value = "user_id",type = IdType.AUTO)
    private String userId;
    @TableField("user_name")
    private String userName;
    @TableField("user_password")
    private String userPassword;
}
