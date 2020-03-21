package com.liuyu.miaosha.dataobject;

import java.util.Date;

public class PromoInfoDO {
    private Integer id;

    private String promoName;

    private Double promoPrice;

    private Date startTime;

    private Date endTime;

    private Integer iteamId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName == null ? null : promoName.trim();
    }

    public Double getPromoPrice() {
        return promoPrice;
    }

    public void setPromoPrice(Double promoPrice) {
        this.promoPrice = promoPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIteamId() {
        return iteamId;
    }

    public void setIteamId(Integer iteamId) {
        this.iteamId = iteamId;
    }
}