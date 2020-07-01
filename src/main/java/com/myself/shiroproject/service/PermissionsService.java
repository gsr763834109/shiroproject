package com.myself.shiroproject.service;

import com.myself.shiroproject.model.Menu;

import java.util.List;

/**
 * @ClassName permissionsService
 * @Description
 * @Author vici
 * @Date 2020/3/30 10:12
 * @Version V2.0
 **/
public interface PermissionsService {
    List<Menu> selectByPrimaryKey(String userName);
}
