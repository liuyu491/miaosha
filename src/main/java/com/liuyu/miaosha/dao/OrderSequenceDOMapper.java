package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.OrderSequenceDO;
import org.springframework.stereotype.Component;

@Component
public interface OrderSequenceDOMapper {
    int insert(OrderSequenceDO record);

    int insertSelective(OrderSequenceDO record);

    int updateValue(int value);

    OrderSequenceDO selectByName(String name);
}