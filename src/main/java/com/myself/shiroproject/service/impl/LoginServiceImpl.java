package com.myself.shiroproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.myself.shiroproject.dao.UserMapper;
import com.myself.shiroproject.model.Role;
import com.myself.shiroproject.model.User;
import com.myself.shiroproject.service.LoginService;
import com.myself.shiroproject.util.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName LoginServiceImpl
 * @Description
 * @Author vici
 * @Date 2020/3/23 18:01
 * @Version V2.0
 **/
@Service
public class LoginServiceImpl extends ServiceImpl<UserMapper,User> implements LoginService{

    @Autowired
    private RedisUtil redisUtil;
    /**
     * 根据用户名查询用户信息
     * @param userName
     */
    @Override
    public User selectUser(String userName) {
        User user = (User) redisUtil.get("user" + userName);
        if(user == null){
            user = this.baseMapper.searchUser(userName);
            redisUtil.set("user" + userName,user,15);
        }
        System.out.println("查询的用户数据"+user);
        return user;
    }

    /**
     * 查询用户的角色
     * @param userName
     * @return
     */
    @Override
    public List<Role> searchUser(String userName) {

        return null;
    }
}
