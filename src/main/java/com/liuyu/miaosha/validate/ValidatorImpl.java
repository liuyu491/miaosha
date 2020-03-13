package com.liuyu.miaosha.validate;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@Component
public class ValidatorImpl implements InitializingBean {
    private Validator validator;

    public ValidateResult validate(Object bean){
        ValidateResult validateResult = new ValidateResult();
        validator.validate(bean);
       /*如果bean的字段校验出错则把错误字段和错误信息存在set中*/
        Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);

        if(constraintViolationSet.size()>0){
            validateResult.setHasError(true);
        }
        /*遍历set，把错误信息存储在validateResult中*/
        constraintViolationSet.forEach(objectConstraintViolation -> {
            String errMsg = objectConstraintViolation.getMessage();
            String propertyName = objectConstraintViolation.getPropertyPath().toString();
            validateResult.setErrorMessage(propertyName,errMsg);
        });
        return validateResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
