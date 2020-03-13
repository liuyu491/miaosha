package com.liuyu.miaosha.service;

import com.liuyu.miaosha.dataobject.CartIteamDO;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.CartIteamModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Component
public interface ShoppingCartService {
    CartIteamDO addIteam(CartIteamDO cartIteamDO) throws BusinessException;
    void deleteIteam(int iteamId);
    List<CartIteamModel> getCartIteamList(int userId);


}
