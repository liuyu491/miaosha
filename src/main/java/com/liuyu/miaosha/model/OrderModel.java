package com.liuyu.miaosha.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderModel {

    //订单id
    private String id;

   //下单用户
    private int userId;

    //订单的总价
    private BigDecimal orderPrice;

    //订单商品总数量
    private int amount;

    //订单中的商品及对应的数量
    List<OrderIteamModel> orderIteams = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<OrderIteamModel> getOrderIteams() {
        return orderIteams;
    }

    public void setOrderIteams(List<OrderIteamModel> orderIteams) {
        this.orderIteams = orderIteams;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderModel{");
        sb.append("id='").append(id).append('\'');
        sb.append(", userId=").append(userId);
        sb.append(", orderPrice=").append(orderPrice);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
