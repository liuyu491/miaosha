package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.OrdersDO;
import org.springframework.stereotype.Component;

@Component
public interface OrdersDOMapper {
    int deleteByPrimaryKey(String id);

    int insert(OrdersDO record);

    int insertSelective(OrdersDO record);

    OrdersDO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrdersDO record);

    int updateByPrimaryKey(OrdersDO record);
}