package com.liuyu.miaosha.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class IteamModel {
    private int id;
    //商品名称
    @NotNull(message = "商品名称不能为空")
    @NotBlank(message = "商品名称不能为空")
    private String title;
//    商品描述
    @NotNull(message = "商品描述不能为空")
    private String description;
//    商品价格
    @NotNull(message = "商品描述不能为空")
    @Min(value = 0,message = "价格不能小于0")
    private BigDecimal price;
//    商品库存
    @NotNull(message = "商品库存不能为空")
    @Min(value = 0,message = "库存不能小于0")
    private Integer stock;
//    商品销量
    private Integer sales;
//  商品图片连接
    @NotNull(message = "商品图片连接不能为空")
    private String imgUrl;

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IteamModel{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", sales=").append(sales);
        sb.append(", imgUrl='").append(imgUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
