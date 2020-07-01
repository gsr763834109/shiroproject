package com.myself.shiroproject.service;

import com.myself.shiroproject.model.Role;
import com.myself.shiroproject.model.User;

import java.util.List;

/**
 * @ClassName LoginService
 * @Description
 * @Author vici
 * @Date 2020/3/23 18:01
 * @Version V2.0
 **/
public interface LoginService {

    User  selectUser(String userName);

    List<Role> searchUser(String userName);

}
