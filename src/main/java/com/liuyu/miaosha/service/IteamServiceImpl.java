package com.liuyu.miaosha.service;

import com.liuyu.miaosha.dao.IteamInfoDOMapper;
import com.liuyu.miaosha.dao.IteamStockDOMapper;
import com.liuyu.miaosha.dataobject.IteamInfoDO;
import com.liuyu.miaosha.dataobject.IteamStockDO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.IteamModel;
import com.liuyu.miaosha.model.PromoModel;
import com.liuyu.miaosha.validate.ValidateResult;
import com.liuyu.miaosha.validate.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Component
public class IteamServiceImpl implements IteamService {

    @Autowired
    ValidatorImpl validator;

    @Autowired
    IteamInfoDOMapper iteamInfoDOMapper;

    @Autowired
    IteamStockDOMapper iteamStockDOMapper;

    @Autowired
    PromoService promoService;

    @Override
    public boolean decreaseStock(int iteamId, int count) {
        if(iteamStockDOMapper.updateByIteamId(iteamId,count)>0)
            return true;
        return false;
    }

    @Override
    @Transient//spirng事务
    public IteamModel createIteam(IteamModel iteamModel) throws BusinessException {
//        校验入参
        ValidateResult validateResult = validator.validate(iteamModel);
        if(validateResult.isHasError()){
            throw new BusinessException(BusinessError.FIELD_CKECK_ERROR,validateResult.getErrorMsg());
        }
//      创建DO
        IteamInfoDO iteamInfoDO = new IteamInfoDO();
        IteamStockDO iteamStockDO = new IteamStockDO();

        BeanUtils.copyProperties(iteamModel,iteamInfoDO);
        BeanUtils.copyProperties(iteamModel,iteamStockDO);
    //BigDecimal转为double
        iteamInfoDO.setPrice(iteamModel.getPrice().doubleValue());

//  插入数据库
        iteamInfoDOMapper.insertSelective(iteamInfoDO);
        iteamStockDO.setIteamId(iteamInfoDO.getId());
        iteamStockDOMapper.insertSelective(iteamStockDO);
//    获取最新数据并返回
        return getIteamById(iteamInfoDO.getId());

    }

    @Override
    @Transient
    public List<IteamModel> getIteamList() {
        List<IteamInfoDO> iteamList = iteamInfoDOMapper.selectIteamList();
        if(iteamList==null){
            return null;
        }
        List<IteamModel> iteamModelList =  iteamList.stream().map(iteamInfoDO -> {
            int stock = iteamStockDOMapper.selectStockByIteamId(iteamInfoDO.getId());
            IteamModel iteamModel = new IteamModel();
            BeanUtils.copyProperties(iteamInfoDO,iteamModel);
            iteamModel.setStock(stock);
            //把价格设置为bigdecimal类型
            iteamModel.setPrice(BigDecimal.valueOf(iteamInfoDO.getPrice()));
            return iteamModel;
        }).collect(Collectors.toList());
        return iteamModelList;
    }

    @Override
    public IteamModel getIteamById(int iteamId) throws BusinessException {
        IteamInfoDO iteamInfoDO = iteamInfoDOMapper.selectByPrimaryKey(iteamId);
        if(iteamInfoDO==null){
            throw new BusinessException(BusinessError.ITEAM_ERROR,"商品不存在");
        }
        IteamStockDO iteamStockDO = iteamStockDOMapper.selectByIteamId(iteamInfoDO.getId());
        IteamModel iteamModel = new IteamModel();
        BeanUtils.copyProperties(iteamInfoDO,iteamModel);
        iteamModel.setPrice(BigDecimal.valueOf(iteamInfoDO.getPrice()));
        if(iteamStockDO==null){
            throw new BusinessException(BusinessError.ITEAM_ERROR,"商品库存查询错误");
        }
        iteamModel.setStock(iteamStockDO.getStock());
        PromoModel promoModel = promoService.getPromoByIteamId(iteamId);
        if(promoModel!=null&&promoModel.getStatus()!=3){
            iteamModel.setPromoModel(promoModel);
        }
        return iteamModel;
    }

    @Override
    public boolean increaseSales(int iteamId, int count) {
        return iteamInfoDOMapper.updateSales(iteamId,count)>0;
    }
}
