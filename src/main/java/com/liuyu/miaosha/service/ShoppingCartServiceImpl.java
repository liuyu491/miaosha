package com.liuyu.miaosha.service;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.liuyu.miaosha.dao.CartIteamDOMapper;
import com.liuyu.miaosha.dao.IteamInfoDOMapper;
import com.liuyu.miaosha.dao.IteamStockDOMapper;
import com.liuyu.miaosha.dataobject.CartIteamDO;
import com.liuyu.miaosha.dataobject.IteamInfoDO;
import com.liuyu.miaosha.dataobject.IteamStockDO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.CartIteamModel;
import com.liuyu.miaosha.model.IteamModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    IteamService iteamService;

    @Autowired
    IteamStockDOMapper iteamStockDOMapper;

    @Autowired
    IteamInfoDOMapper iteamInfoDOMapper;

    @Autowired
    CartIteamDOMapper cartIteamDOMapper;


    @Override
    @Transactional
    public CartIteamDO addIteam(CartIteamDO cartIteamDO) throws BusinessException {
        //校验入参
        if(cartIteamDO==null){
            throw new BusinessException(BusinessError.SHOPPING_CART_ERROR,"购物条目错误");
        }
        //判断加入购物车的商品是否存在
        IteamModel iteamModel = iteamService.getIteamById(cartIteamDO.getIteamId());
        if(cartIteamDO.getCount()<1){
            throw new BusinessException(BusinessError.SHOPPING_CART_ERROR,"商品数目不能小于0");
        }

        if(cartIteamDO.getCount()>1000){
            throw new BusinessException(BusinessError.SHOPPING_CART_ERROR,"商品数目不能大于1000");
        }
        int stock = iteamStockDOMapper.selectStockByIteamId(cartIteamDO.getIteamId());
        if(stock<cartIteamDO.getCount())
            throw new BusinessException(BusinessError.SHOPPING_CART_ERROR,"商品库存不足");

        //插入数据库
        cartIteamDOMapper.insertSelective(cartIteamDO);

        //返回结果
        CartIteamDO cartIteamDO1 = cartIteamDOMapper.selectByPrimaryKey(cartIteamDO.getId());
        if(cartIteamDO1==null){
            throw new BusinessException(BusinessError.SHOPPING_CART_ERROR,"添加购物车失败");
        }
        return cartIteamDO1;
    }

    @Override
    public void deleteIteam(int iteamId) {

    }

    @Override
    public List<CartIteamModel> getCartIteamList(int userId) {
       List<CartIteamDO> list = cartIteamDOMapper.selectCartIteamDOListByUserId(userId);
       List<CartIteamModel>  cartIteamModelList = list.stream().map(cartIteamDO -> {
            CartIteamModel cartIteamModel = new CartIteamModel();
            IteamInfoDO iteamInfoDO = iteamInfoDOMapper.selectByPrimaryKey(cartIteamDO.getIteamId());
            cartIteamModel.setImgUrl(iteamInfoDO.getImgUrl());
            cartIteamModel.setIteamId(iteamInfoDO.getId());
            cartIteamModel.setCount(cartIteamDO.getCount());
            cartIteamModel.setSinglePrice(BigDecimal.valueOf(iteamInfoDO.getPrice()));
            return cartIteamModel;
        }).collect(Collectors.toList());
        return cartIteamModelList;
    }

}
