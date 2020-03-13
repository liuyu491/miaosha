package com.liuyu.miaosha.service;

import com.liuyu.miaosha.dataobject.UserInfoDO;
import com.liuyu.miaosha.dataobject.UserPasswordDO;
import com.liuyu.miaosha.model.UserModel;

public interface UserService {
    UserModel getUserModelByUserId(int userId);
    UserInfoDO getUserInfoDOById(int userId);
    UserPasswordDO getUserPasswordDOByUserId(int userId);
    UserInfoDO getUserInfoDOByUserName(String userName);
    boolean regist(UserModel userModel);
    UserModel login(String email,String password);
}
