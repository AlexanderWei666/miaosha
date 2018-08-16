package com.alexander.miaosha.dao;

import com.alexander.miaosha.domain.MiaoshaUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MiaoshaUserDao {

    public MiaoshaUser getById(@Param("id") Long id);
}
