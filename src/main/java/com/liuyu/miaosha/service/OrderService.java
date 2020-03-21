package com.liuyu.miaosha.service;

import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.CartIteamModel;
import com.liuyu.miaosha.model.OrderIteamModel;
import com.liuyu.miaosha.model.OrderModel;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public interface OrderService {
    OrderModel createOrder(OrderIteamModel orderIteamModel) throws BusinessException;
    OrderModel createOrder(List<CartIteamModel> cartIteamModelList);
    OrderIteamModel createOrderIteam(int iteamId, int userId, int count, Integer promoId) throws BusinessException;
    OrderModel getOrderById();
}
