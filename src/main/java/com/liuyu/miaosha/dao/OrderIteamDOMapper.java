package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.OrderIteamDO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface OrderIteamDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderIteamDO record);

    int insertSelective(OrderIteamDO record);

    OrderIteamDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderIteamDO record);

    int updateByPrimaryKey(OrderIteamDO record);
}