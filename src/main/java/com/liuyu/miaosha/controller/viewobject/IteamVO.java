package com.liuyu.miaosha.controller.viewobject;


import java.math.BigDecimal;

public class IteamVO {
    private int id;
    //商品名称

    private String title;
    //    商品描述

    private String description;
    //    商品价格


    private BigDecimal price;
    //    商品库存

    private Integer stock;
    //    商品销量
    private Integer sales;
    //  商品图片连接

    private String imgUrl;

    //秒杀活动Id
    private int promoId;

    //秒杀活动价格
    private BigDecimal promoPrice;

    //秒杀活动开始时间
    private String startDate;

    //秒杀活动结束时间
    private String endDate;

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //秒杀活动状态 0表示没有秒杀活动，1表示秒杀活动未开始，2表是正在进行，3表示结束
    private int status;

    public int getPromoId() {
        return promoId;
    }

    public void setPromoId(int promoId) {
        this.promoId = promoId;
    }

    public BigDecimal getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(BigDecimal promoPrice) {
        this.promoPrice = promoPrice;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
