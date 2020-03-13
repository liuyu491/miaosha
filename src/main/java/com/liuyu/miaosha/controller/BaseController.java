package com.liuyu.miaosha.controller;

import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.response.CommonReturnType;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;

public class BaseController {
    public static final String CONTENT_TYPE_FROMED = "application/x-www-form-urlencoded";



    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonReturnType exceptionHandle(Exception ex){
        CommonReturnType response = new CommonReturnType();
        HashMap<String,String> data = new HashMap<>();
        response.setStatus("fail");

        if(ex instanceof BusinessException) {
            BusinessException busex = (BusinessException) ex;
            data.put("errorCode", busex.getErrorCode());
            data.put("errorMassage", busex.getErrorMassage());
        }
        else{
            data.put("errorCode", BusinessError.UNKNOWN_ERROR.getErrorCode());
            data.put("errorMassage", BusinessError.UNKNOWN_ERROR.getErrorMassage());
        }
        response.setData(data);
        return response;
    }
}
