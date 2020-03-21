package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.PromoInfoDO;

public interface PromoInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PromoInfoDO record);

    int insertSelective(PromoInfoDO record);

    PromoInfoDO selectByPrimaryKey(Integer id);

    PromoInfoDO selectByIteamId(Integer iteamId);

    int updateByPrimaryKeySelective(PromoInfoDO record);

    int updateByPrimaryKey(PromoInfoDO record);
}