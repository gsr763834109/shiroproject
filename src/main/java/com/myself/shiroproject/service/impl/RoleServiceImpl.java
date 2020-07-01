package com.myself.shiroproject.service.impl;

import com.myself.shiroproject.dao.RoleMapper;
import com.myself.shiroproject.model.Role;
import com.myself.shiroproject.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description
 * @Author vici
 * @Date 2020/3/27 11:56
 * @Version V2.0
 **/
@Service
public class RoleServiceImpl implements RoleService{


    @Autowired
    private RoleMapper roleMapper;
    /**
     * 根据用户查询角色
     * @param userName
     * @return
     */
    @Override
    public List<Role> searchRoleByUser(String userName) {
        return roleMapper.selectByUserName(userName);
    }
}
