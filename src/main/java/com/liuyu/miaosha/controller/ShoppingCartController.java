package com.liuyu.miaosha.controller;


import com.liuyu.miaosha.dataobject.CartIteamDO;
import com.liuyu.miaosha.dataobject.IteamInfoDO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.CartIteamModel;
import com.liuyu.miaosha.model.IteamModel;
import com.liuyu.miaosha.response.CommonReturnType;
import com.liuyu.miaosha.service.IteamService;
import com.liuyu.miaosha.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
@RequestMapping("/cart")
public class ShoppingCartController extends BaseController {
    @Autowired
    ShoppingCartService shoppingCartService;


    @Autowired
    IteamService iteamService;

    @RequestMapping("/add")
    @ResponseBody
    public CommonReturnType addIteam2Cart(int iteamId,int userId,int count) throws BusinessException {
        CartIteamDO cartIteamDO = new CartIteamDO();
        cartIteamDO.setIteamId(iteamId);
        cartIteamDO.setCount(count);
        cartIteamDO.setUserId(userId);
        shoppingCartService.addIteam(cartIteamDO);
        return new CommonReturnType("success",null);
    }

}
