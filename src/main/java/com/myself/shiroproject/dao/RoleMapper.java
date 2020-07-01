package com.myself.shiroproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.shiroproject.model.Role;

import java.util.List;

/**
 * @ClassName User
 * @Description
 * @Author vici
 * @Date 2020/3/24 14:06
 * @Version V2.0
 **/
public interface RoleMapper extends BaseMapper<Role>{

    /**
     * 查询用户的角色
     * @param userName
     * @return
     */
    List<Role> selectByUserName(String userName);



}
