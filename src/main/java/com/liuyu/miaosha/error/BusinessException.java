package com.liuyu.miaosha.error;

public class BusinessException extends Exception implements CommonError {
    private CommonError commonError;

    //通过错误类型构造异常
    public BusinessException(CommonError commonError){
        //要调用父类的构造函数
        super();
        this.commonError = commonError;
    }

    //构造带有自己错误信息的异常
    public BusinessException(CommonError commonError,String massage){
        super();
        this.commonError = commonError;
        commonError.setErrorMassage(massage);
    }


    @Override
    public String getErrorCode() {
        return commonError.getErrorCode();
    }

    @Override
    public String getErrorMassage() {
        return commonError.getErrorMassage();
    }

    @Override
    public CommonError setErrorMassage(String massage) {
        return commonError.setErrorMassage(massage);
    }
}
