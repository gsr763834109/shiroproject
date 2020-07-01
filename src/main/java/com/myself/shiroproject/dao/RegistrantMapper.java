package com.myself.shiroproject.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @ClassName PermissionsMapper
 * @Description
 * @Author vici
 * @Date 2020/3/27 14:29
 * @Version V2.0
 **/
public interface RegistrantMapper extends BaseMapper<RegistrantMapper> {

 List<RegistrantMapper> selectAll();

}
