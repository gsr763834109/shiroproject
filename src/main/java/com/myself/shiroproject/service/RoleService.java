package com.myself.shiroproject.service;

import com.myself.shiroproject.model.Role;

import java.util.List;

/**
 * @ClassName RoleService
 * @Description
 * @Author vici
 * @Date 2020/3/27 11:55
 * @Version V2.0
 **/
public interface RoleService {

    List<Role>  searchRoleByUser(String userName);

}
