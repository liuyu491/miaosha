package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.IteamInfoDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IteamInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(IteamInfoDO record);

    int insertSelective(IteamInfoDO record);

    IteamInfoDO selectByPrimaryKey(Integer id);

    List<IteamInfoDO> selectIteamList();

    int updateByPrimaryKeySelective(IteamInfoDO record);

    int updateByPrimaryKeyWithBLOBs(IteamInfoDO record);

    int updateByPrimaryKey(IteamInfoDO record);

    int updateSales(@Param("iteamId")int iteamId,@Param("count")int count);
}