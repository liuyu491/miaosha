package com.liuyu.miaosha.service;

import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.IteamModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface IteamService {
    IteamModel createIteam(IteamModel iteamModel) throws BusinessException;

    List<IteamModel> getIteamList();

    IteamModel getIteamById(int iteamId) throws BusinessException;

    boolean decreaseStock(int iteamId,int count);

    boolean increaseSales(int iteamId,int count);
}
