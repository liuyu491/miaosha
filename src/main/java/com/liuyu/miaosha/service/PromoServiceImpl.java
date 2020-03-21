package com.liuyu.miaosha.service;


import com.liuyu.miaosha.dao.PromoInfoDOMapper;
import com.liuyu.miaosha.dataobject.PromoInfoDO;

import com.liuyu.miaosha.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.DateTimeException;

@Component
@Service
public class PromoServiceImpl implements PromoService{
    @Autowired
    PromoInfoDOMapper promoInfoDOMapper;


    @Override
    public PromoModel addPromo(PromoInfoDO promoInfoDO) {
        return null;
    }

    @Override
    public PromoModel getPromoByIteamId(int iteamId) {

        PromoInfoDO promoInfoDO = promoInfoDOMapper.selectByIteamId(iteamId);
        if(promoInfoDO==null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoInfoDO,promoModel);
        promoModel.setPromoPrice(BigDecimal.valueOf(promoInfoDO.getPromoPrice()));

        DateTime now = DateTime.now();
        DateTime startTime = new DateTime(promoInfoDO.getStartTime());
        DateTime endTime = new DateTime(promoInfoDO.getEndTime());
        promoModel.setStartTime(startTime);
        promoModel.setEndTime(endTime);
        setPromoStatus(promoModel, now, startTime, endTime);

        return promoModel;
    }

    @Override
    public PromoModel getPromoById(int id) {
        PromoInfoDO promoInfoDO = promoInfoDOMapper.selectByPrimaryKey(id);
        if(promoInfoDO==null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoInfoDO,promoModel);
        promoModel.setPromoPrice(BigDecimal.valueOf(promoInfoDO.getPromoPrice()));

        DateTime now = DateTime.now();
        DateTime startTime = new DateTime(promoInfoDO.getStartTime());
        DateTime endTime = new DateTime(promoInfoDO.getEndTime());
        promoModel.setStartTime(startTime);
        promoModel.setEndTime(endTime);
        setPromoStatus(promoModel, now, startTime, endTime);

        return promoModel;
    }

    private void setPromoStatus(PromoModel promoModel, DateTime now, DateTime startTime, DateTime endTime) {
        if(now.isBefore(startTime)){
            promoModel.setStatus(1);
        }
        else if(now.isAfter(endTime)){
            promoModel.setStatus(3);
        }
        else{
            promoModel.setStatus(2);
        }
    }
}
