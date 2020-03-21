package com.liuyu.miaosha.controller;

import com.liuyu.miaosha.controller.viewobject.IteamVO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.IteamModel;
import com.liuyu.miaosha.response.CommonReturnType;
import com.liuyu.miaosha.service.IteamService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/iteam")
@CrossOrigin(allowCredentials = "true",allowedHeaders ="*")
public class IteamController extends BaseController {

    @Autowired
    IteamService iteamService;

    @RequestMapping(value = "/create",method = RequestMethod.POST,consumes = CONTENT_TYPE_FROMED )
    @ResponseBody
    public CommonReturnType create(IteamModel iteamModel) throws BusinessException {
        //System.out.println(request.getParameterMap());
        IteamModel iteamModel1 = iteamService.createIteam(iteamModel);
        CommonReturnType commonReturnType = new CommonReturnType("success",convertVOFromIteamModel(iteamModel));
        return commonReturnType;
    }


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getIteam(@RequestParam("id") int id) throws BusinessException {
        IteamModel iteamModel = iteamService.getIteamById(id);

        return new CommonReturnType("success",convertVOFromIteamModel(iteamModel));
    }

    @RequestMapping(value = "/getlist",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getIteamList() throws BusinessException {
        List<IteamModel> iteamModelList = iteamService.getIteamList();
        if(iteamModelList==null){
            throw new BusinessException(BusinessError.ITEAM_ERROR,"商品库中没有商品");
        }
        List<IteamVO> iteamVOS = iteamModelList.stream().map(
                this::convertVOFromIteamModel
        ).collect(Collectors.toList());
        System.out.println(iteamVOS.get(4).getPrice());
        return new CommonReturnType("success",iteamVOS);
    }

    private IteamVO convertVOFromIteamModel(IteamModel iteamModel){
        IteamVO iteamVO = new IteamVO();
        BeanUtils.copyProperties(iteamModel,iteamVO);
        if(iteamModel.getPromoModel()==null){
            iteamVO.setStatus(0);
            return iteamVO;
        }
        else{
            iteamVO.setStatus(iteamModel.getPromoModel().getStatus());
            iteamVO.setPromoPrice(iteamModel.getPromoModel().getPromoPrice());
            DateTime startTime = iteamModel.getPromoModel().getStartTime();
            DateTime endTime = iteamModel.getPromoModel().getEndTime();
            iteamVO.setPromoId(iteamModel.getPromoModel().getId());
            iteamVO.setStartDate(startTime.toString("yyyy/MM/dd hh:mm:ss"));
            iteamVO.setEndDate(endTime.toString("yyyy/MM/dd hh:mm:ss"));
            return iteamVO;
        }

    }
}
