package com.liuyu.miaosha.model;

import java.math.BigDecimal;

public class CartIteamModel {
    private Integer id;

    private Integer iteamId;

    private Integer userId;

    private Integer count;

    private String imgUrl;

    private BigDecimal singlePrice;

    private BigDecimal totlePrice;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }



    public BigDecimal getSinglePrice() {
        return singlePrice;
    }

    public void setSinglePrice(BigDecimal singlePrice) {
        this.singlePrice = singlePrice;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIteamId() {
        return iteamId;
    }

    public void setIteamId(Integer iteamId) {
        this.iteamId = iteamId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotlePrice() {
        return singlePrice.multiply(BigDecimal.valueOf(count));
    }
}