package com.myself.shiroproject.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.shiroproject.dao.PermissionsMapper;
import com.myself.shiroproject.model.Menu;
import com.myself.shiroproject.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName permissionsServiceImpl
 * @Description
 * @Author vici
 * @Date 2020/3/30 10:13
 * @Version V2.0
 **/
@Service
public class permissionsServiceImpl  extends ServiceImpl<PermissionsMapper,Menu> implements PermissionsService {

    @Autowired
    private PermissionsMapper permissionsMapper;

    public List<Menu>  selectByPrimaryKey(String userName){
        List<Menu> selectByPrimaryKey = permissionsMapper.selectByPrimaryKey(userName);
        return selectByPrimaryKey;
    }



}
