package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.CartIteamDO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Component
public interface CartIteamDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CartIteamDO record);

    int insertSelective(CartIteamDO record);

    CartIteamDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CartIteamDO record);

    int updateByPrimaryKey(CartIteamDO record);

    List<CartIteamDO> selectCartIteamDOListByUserId(int userId);
}