package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.IteamStockDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface IteamStockDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IteamStockDO record);

    int insertSelective(IteamStockDO record);

    IteamStockDO selectByPrimaryKey(Integer id);

    IteamStockDO selectByIteamId(Integer IteamId);

    int selectStockByIteamId(int id);


    int updateByPrimaryKeySelective(IteamStockDO record);

    int updateByPrimaryKey(IteamStockDO record);

    int updateByIteamId(@Param("iteamId") Integer iteamId,@Param("count") Integer count);

}