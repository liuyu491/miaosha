package com.liuyu.miaosha.controller;

import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.OrderIteamModel;
import com.liuyu.miaosha.model.OrderModel;
import com.liuyu.miaosha.model.UserModel;
import com.liuyu.miaosha.response.CommonReturnType;
import com.liuyu.miaosha.service.OrderService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
@CrossOrigin(allowedHeaders = "*",allowCredentials = "true")
public class OrderController extends BaseController {
    @Autowired
    OrderService orderService;


    @RequestMapping("/create")
    @ResponseBody
    @Transactional
    public CommonReturnType create(@RequestParam("id") int iteamId, @RequestParam("count") int count, HttpServletRequest httpServletRequest) throws BusinessException {
        HttpSession session = httpServletRequest.getSession();
        Object user = session.getAttribute("LOGINUSER");
        if(user==null){
            throw new BusinessException(BusinessError.USER_NOT_LOGIN);
        }


        OrderIteamModel orderIteamModel = orderService.createOrderIteam(iteamId,((UserModel)user).getId(),count);
        OrderModel orderModel = orderService.createOrder(orderIteamModel);
        return new CommonReturnType("success",orderIteamModel);
    }
}
