package com.liuyu.miaosha.dao;

import com.liuyu.miaosha.dataobject.UserInfoDO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface UserInfoDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserInfoDO record);

    int insertSelective(UserInfoDO record);

    UserInfoDO selectByPrimaryKey(Integer id);

    UserInfoDO selectByName(String userName);

    UserInfoDO selectByEmail(String email);

    int updateByPrimaryKeySelective(UserInfoDO record);

    int updateByPrimaryKey(UserInfoDO record);

}