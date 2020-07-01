package com.myself.shiroproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.myself.shiroproject.model.User;

/**
 * @ClassName UserMapper
 * @Description
 * @Author vici
 * @Date 2020/3/24 11:59
 * @Version V2.0
 **/
public interface UserMapper extends BaseMapper<User>{

    User searchUser(String userName);
}
