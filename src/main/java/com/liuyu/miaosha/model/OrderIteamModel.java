package com.liuyu.miaosha.model;

import java.math.BigDecimal;

public class OrderIteamModel {
    //id
    private Integer id;
//  商品id
    private Integer iteamId;
//  订单id
    private String orderId;
//用户id
    private Integer userId;
//下单时商品的价格
    private BigDecimal iteamPrice;
//下单时商品的图片
    private String imgUrl;
//购买的商品的数量
    private Integer count;


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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getIteamPrice() {
        return iteamPrice;
    }

    public void setIteamPrice(BigDecimal iteamPrice) {
        this.iteamPrice = iteamPrice;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}