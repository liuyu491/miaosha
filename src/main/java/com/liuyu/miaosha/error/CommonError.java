package com.liuyu.miaosha.error;
//CommonError
public interface CommonError {
    String getErrorCode();

    String getErrorMassage();

    CommonError setErrorMassage(String massage);
}
