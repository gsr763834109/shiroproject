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
 * @ClassName Menu
 * @Description
 * @Author vici
 * @Date 2020/3/23 17:57
 * @Version V2.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName(value = "menu")
public class Menu implements Serializable{
    @TableId(value = "menu_id",type = IdType.AUTO)
    private String menuId;

    @TableField("menu_name")
    private String menuName;

    @TableField("menu_url")
    private String menuUrl;

    @TableField("menu_pid")
    private String menuPid;

    @TableField("menu_perms")
    private String menuPerms;

    @TableField("menu_icon")
    private String menuIcon;

    @TableField("menu_creat_time")
    private String menuCreatTime;

    @TableField("menu_update_time")
    private String menuUpdateTime;




}
