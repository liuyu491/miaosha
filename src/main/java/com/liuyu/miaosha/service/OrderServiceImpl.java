package com.liuyu.miaosha.service;

import com.liuyu.miaosha.dao.IteamInfoDOMapper;
import com.liuyu.miaosha.dao.OrderSequenceDOMapper;
import com.liuyu.miaosha.dao.OrdersDOMapper;
import com.liuyu.miaosha.dao.OrderIteamDOMapper;

import com.liuyu.miaosha.dataobject.IteamInfoDO;
import com.liuyu.miaosha.dataobject.OrderSequenceDO;
import com.liuyu.miaosha.dataobject.OrdersDO;
import com.liuyu.miaosha.dataobject.OrderIteamDO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.CartIteamModel;
import com.liuyu.miaosha.model.IteamModel;
import com.liuyu.miaosha.model.OrderIteamModel;
import com.liuyu.miaosha.model.OrderModel;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.management.StringValueExp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {
    @Autowired
    IteamInfoDOMapper iteamInfoDOMapper;

    @Autowired
    IteamService iteamService;

    @Autowired
    OrderIteamDOMapper orderIteamDOMapper;

    @Autowired
    OrdersDOMapper ordersDOMapper;

    @Autowired
    OrderSequenceDOMapper orderSequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(OrderIteamModel orderIteamModel) throws BusinessException {
        /*校验订单项是否合法*/
        if(iteamInfoDOMapper.selectByPrimaryKey(orderIteamModel.getIteamId())==null){
            throw new BusinessException(BusinessError.ORDER_ERROR,"没有该商品");
        }
        if(orderIteamModel.getCount()<1||orderIteamModel.getCount()>10000){
            throw new BusinessException(BusinessError.ORDER_ERROR,"购买数量错误");
        }
        //下单锁库存
        if(!iteamService.decreaseStock(orderIteamModel.getIteamId(),orderIteamModel.getCount()))
            throw new BusinessException(BusinessError.ORDER_ERROR,"商品库存不足");
        iteamService.increaseSales(orderIteamModel.getIteamId(),orderIteamModel.getCount());
        //形成订单编号
        //订单编号格式：共24位，前14位用来表示时间，中间6位表示订单序号，最后两位用来表示订单属于的分区段
        String orderId = createOrderId();


        //入库
        OrderIteamDO orderIteamDO = new OrderIteamDO();

        orderIteamDO.setImgUrl(orderIteamModel.getImgUrl());
        orderIteamDO.setCount(orderIteamModel.getCount());
        orderIteamDO.setIteamPrice(orderIteamModel.getIteamPrice().doubleValue());
        orderIteamDO.setIteamId(orderIteamModel.getIteamId());
        orderIteamDO.setOrderId(orderId);
        orderIteamDO.setUserId(orderIteamModel.getUserId());
        orderIteamDOMapper.insertSelective(orderIteamDO);
        OrdersDO order = new OrdersDO();
        order.setId(orderId);
        order.setAmount(orderIteamModel.getCount());
        order.setOrderPrice(orderIteamModel.getIteamPrice().multiply(BigDecimal.valueOf(orderIteamModel.getCount())).doubleValue());
        order.setUserId(orderIteamModel.getUserId());
        ordersDOMapper.insert(order);

        OrderModel orderModel = new OrderModel();
        BeanUtils.copyProperties(order,orderModel);
        orderModel.setOrderPrice(BigDecimal.valueOf(order.getOrderPrice()));
        List<OrderIteamModel> list = orderModel.getOrderIteams();
        list.add(orderIteamModel);

        return orderModel;
    }

    @Override
    public OrderModel createOrder(List<CartIteamModel> cartIteamModelList) {
        return null;
    }

    @Override
    public OrderIteamModel createOrderIteam(int iteamId, int userId, int count) throws BusinessException {
        IteamInfoDO iteamInfoDO = iteamInfoDOMapper.selectByPrimaryKey(iteamId);
        if(iteamInfoDO==null){
            throw new BusinessException(BusinessError.ITEAM_ERROR,"商品不存在");
        }
        OrderIteamModel orderIteamModel = new OrderIteamModel();
        BeanUtils.copyProperties(iteamInfoDO,orderIteamModel);
        orderIteamModel.setCount(count);
        orderIteamModel.setIteamId(iteamId);
        orderIteamModel.setIteamPrice(BigDecimal.valueOf(iteamInfoDO.getPrice()));
        orderIteamModel.setImgUrl(iteamInfoDO.getImgUrl());
        orderIteamModel.setUserId(userId);
        return orderIteamModel;
    }

    @Override
    public OrderModel getOrderById() {
        return null;
    }


    /*设置事务属性propagation = Propagation.REQUIRES_NEW表示
    * 在该段代码执行完成后就提交，不用等外部的事务完成才提交*/
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String createOrderId(){
        LocalDateTime localDateTime  = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");
        String date = formatter.format(localDateTime);
        StringBuilder sequence = new StringBuilder(date);
        OrderSequenceDO orderSequenceDO = orderSequenceDOMapper.selectByName("sequence");
        int value = orderSequenceDO.getCurrentValue();
        int nextValue = orderSequenceDO.getCurrentValue()+orderSequenceDO.getStep();
        if(value==999999){
            nextValue = 0;
        }
        for(int i=0;i<6-nextValue/10-1;i++){
            sequence.append(0);
        }
        sequence.append(nextValue);
        orderSequenceDOMapper.updateValue(nextValue);
        sequence.append("00");
        return sequence.toString();
    }
}
