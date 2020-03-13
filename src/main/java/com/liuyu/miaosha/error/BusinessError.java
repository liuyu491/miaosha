package com.liuyu.miaosha.error;

public enum BusinessError implements CommonError{
    //通用错误类型00001
    BUSINESS_ERROR("00001","参数不合法"),
    //用户不存在
    USER_NOT_EXIST("10001","用户不存在"),

    //用户未登录
    USER_NOT_LOGIN("10002","用户没有登录"),

    //未知错误
    UNKNOWN_ERROR("20000","未知错误"),
    //密码或账号错误
    WRONG_ACCOUNT_OR_PASSWORD("20001","密码或账号错误"),
    //字段校验错误
    FIELD_CKECK_ERROR("30000","字段校验错误"),
    //商品通用错误
    ITEAM_ERROR("40000","商品通用错误"),
    //购物车通用错误
    SHOPPING_CART_ERROR("50000","购物车通用错误"),

    ORDER_ERROR("60000","订单通用错误")
    ;

    private String errorCode;
    private String errorMassge;
    private BusinessError(String errorCode,String errorMassge){
        this.errorCode = errorCode;
        this.errorMassge = errorMassge;
    }
    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMassage() {
        return errorMassge;
    }

    @Override
    public CommonError setErrorMassage(String massage) {
        this.errorMassge = massage;
        return this;
    }
}
