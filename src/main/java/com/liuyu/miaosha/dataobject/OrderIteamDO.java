package com.liuyu.miaosha.dataobject;

public class OrderIteamDO {
    private Integer id;

    private Integer iteamId;

    private String orderId;

    private Integer userId;

    private Double iteamPrice;

    private String imgUrl;

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

    public Double getIteamPrice() {
        return iteamPrice;
    }

    public void setIteamPrice(Double iteamPrice) {
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