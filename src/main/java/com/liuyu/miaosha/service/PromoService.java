package com.liuyu.miaosha.service;


import com.liuyu.miaosha.dataobject.PromoInfoDO;
import com.liuyu.miaosha.model.PromoModel;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public interface PromoService {
    PromoModel addPromo(PromoInfoDO promoInfoDO);

    PromoModel getPromoByIteamId(int iteamId);

    PromoModel getPromoById(int id);


}
    


