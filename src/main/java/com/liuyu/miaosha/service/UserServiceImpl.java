package com.liuyu.miaosha.service;

import com.liuyu.miaosha.dao.UserInfoDOMapper;
import com.liuyu.miaosha.dao.UserPasswordDOMapper;
import com.liuyu.miaosha.dataobject.UserInfoDO;
import com.liuyu.miaosha.dataobject.UserPasswordDO;
import com.liuyu.miaosha.model.UserModel;
import com.liuyu.miaosha.utils.MiaoShaUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    UserInfoDOMapper userInfoDOMapper;

    @Override
    public UserModel getUserModelByUserId(int userId) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(userId);
        UserModel userModel = new UserModel();
        UserPasswordDO userPasswordDO = null;
        if(userInfoDO!=null){
            userPasswordDO = userPasswordDOMapper.selectByUserId(userInfoDO.getId());
            BeanUtils.copyProperties(userInfoDO,userModel);
        }
        if(userPasswordDO!=null){
            userModel.setEncrptPassword(userPasswordDO.getEncrptPassword());
            return userModel;
        }

        return null;
    }

    @Override
    public UserInfoDO getUserInfoDOById(int userId) {
        UserInfoDO userInfoDO = userInfoDOMapper.selectByPrimaryKey(userId);
        return userInfoDO;
    }

    @Override
    public UserPasswordDO getUserPasswordDOByUserId(int userId) {
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userId);
        return userPasswordDO;
    }

    @Override
    public UserInfoDO getUserInfoDOByUserName(String userName) {
        if(userName!=null){
            return userInfoDOMapper.selectByName(userName);
        }
        return null;
    }

    @Override
    public UserModel login(String email, String password) {
        String encrptPassword = MiaoShaUtils.MD5(password);
        UserModel userModel = null;
        UserInfoDO userInfoDO = userInfoDOMapper.selectByEmail(email);
        if(userInfoDO==null){
            return null;
        }
        UserPasswordDO userPasswordDO = userPasswordDOMapper.selectByUserId(userInfoDO.getId());

        if(!userPasswordDO.getEncrptPassword().equals(encrptPassword)){
            return null;
        }
        userModel = new UserModel();
        BeanUtils.copyProperties(userInfoDO,userModel);
        userModel.setEncrptPassword(encrptPassword);
        return userModel;
    }

    @Override
    public boolean regist(UserModel userModel) {
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(userModel,userInfoDO);
        UserPasswordDO userPasswordDO = new UserPasswordDO();
        int a = userInfoDOMapper.insertSelective(userInfoDO);
        userPasswordDO.setUserId(userInfoDO.getId());
        userPasswordDO.setEncrptPassword(userModel.getEncrptPassword());
        int b = userPasswordDOMapper.insertSelective(userPasswordDO);
        if(a>0&&b>0){
            return true;
        }
        return false;
    }
}

