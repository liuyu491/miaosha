package com.liuyu.miaosha.model;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCartModel {
    private List<CartIteamModel> cartIteamModelList;
    private int userId;
    private int totalCount;
    private BigDecimal totalPrice;

    public List<CartIteamModel> getCartIteamModelList() {
        return cartIteamModelList;
    }

    public void setCartIteamModelList(List<CartIteamModel> cartIteamModelList) {
        this.cartIteamModelList = cartIteamModelList;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal price = BigDecimal.valueOf(0);
        cartIteamModelList.forEach(cartIteamModel -> {
            price.add(cartIteamModel.getTotlePrice());
        });
        return price;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
