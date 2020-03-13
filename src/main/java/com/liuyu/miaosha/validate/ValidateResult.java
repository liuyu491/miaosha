package com.liuyu.miaosha.validate;


import io.netty.util.internal.StringUtil;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ValidateResult {
    private boolean hasError;
    private Map<String,String> errorMessage = new HashMap<>();

    public boolean isHasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }


    public void setErrorMessage(String field,String errorMsg) {
        this.errorMessage.put(field,errorMsg);
    }

    public String getErrorMsg(){
        return     StringUtils.join(errorMessage.values(),",");
    }

}
