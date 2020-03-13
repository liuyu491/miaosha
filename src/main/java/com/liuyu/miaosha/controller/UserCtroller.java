package com.liuyu.miaosha.controller;

import com.liuyu.miaosha.controller.viewobject.UserVO;
import com.liuyu.miaosha.error.BusinessError;
import com.liuyu.miaosha.error.BusinessException;
import com.liuyu.miaosha.model.UserModel;
import com.liuyu.miaosha.response.CommonReturnType;
import com.liuyu.miaosha.service.MailService;
import com.liuyu.miaosha.service.UserService;
import com.liuyu.miaosha.utils.MiaoShaUtils;
import com.liuyu.miaosha.validate.ValidateResult;
import com.liuyu.miaosha.validate.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class UserCtroller extends BaseController {

    @Autowired
    UserService userService;

    //HttpServletRequest中使用ThreadLocal存取不同线程的Session
    @Autowired
    HttpServletRequest httpServletRequest;

    @Autowired
    MailService mailService;

    @Autowired
    ValidatorImpl validator;


    @RequestMapping(value = "/getotp",method = RequestMethod.POST,consumes = CONTENT_TYPE_FROMED)
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam("email") String email){
        //生成随机码
        Random random = new Random();
        int randomNum = random.nextInt(9999);
        String optCode = String.valueOf(1000+randomNum);
        HttpSession session = httpServletRequest.getSession();
        //将随机码存在Session中
        session.setAttribute(email,optCode);
        //将随机码下发给用户
        CommonReturnType response = new CommonReturnType();
        Map<String,String> data = new HashMap<>();
        data.put("errorCode","20000");
        data.put("errorMassage","邮件发送失败");
        try {

            mailService.sendSimpleMail(email,"秒杀系统验证码","您此次登录的验证码是："+optCode);
            response.setStatus("success");
        } catch (Exception e) {
            response.setStatus("fail");
            response.setData(data);
        }

        return response;
    }

    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam("id")int id) throws BusinessException {
        UserModel userModel = userService.getUserModelByUserId(id);
        if(userModel==null){
            throw new BusinessException(BusinessError.USER_NOT_EXIST);
        }

        CommonReturnType commonReturnType = new CommonReturnType("success",convertFromModel(userModel));
        return commonReturnType;
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST, consumes = CONTENT_TYPE_FROMED)
    @ResponseBody
    public CommonReturnType register(/*@RequestParam("email")String email,@RequestParam("otp")String otp,@RequestParam("name")String name,@RequestParam("telphone")String telphone,@RequestParam("password")String password,@RequestParam("age")String age,@RequestParam("gender")String gender ,*/HttpServletRequest request) throws BusinessException {
        Map<String,String[]> params = httpServletRequest.getParameterMap();
        HttpSession session = httpServletRequest.getSession();
        String otp = params.get("otp")[0];
        String email = params.get("email")[0];
        String name = params.get("name")[0];
        String gerder = params.get("gender")[0];
        String password = params.get("password")[0];
        String age = params.get("age")[0];
        String telphone = params.get("telphone")[0];

        if(otp.equals("")){
           throw new BusinessException(BusinessError.BUSINESS_ERROR,"验证码不能为空");
        }
        if(otp.equals(session.getAttribute("otp"))){
            throw new BusinessException(BusinessError.BUSINESS_ERROR,"验证码错误");
        }

        UserModel userModel = new UserModel();


        int age1 = 0;
        try {
            age1 = Integer.valueOf(age);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new BusinessException(BusinessError.BUSINESS_ERROR,"输入的年龄格式不正确");
        }
        userModel.setAge(age1);
        userModel.setName(name);
        userModel.setEmail(email);
        userModel.setGender(Byte.valueOf(gerder));
        userModel.setTelphone(telphone);
        if(password==null||password.equals(""))
            throw new BusinessException(BusinessError.BUSINESS_ERROR,"密码不能为空");
        String encrptPassword = MiaoShaUtils.MD5(password);
        userModel.setEncrptPassword(encrptPassword);
        ValidateResult validateResult = validator.validate(userModel);
        if(validateResult.isHasError()){
            throw new BusinessException(BusinessError.BUSINESS_ERROR,validateResult.getErrorMsg());
        }
        boolean flag = userService.regist(userModel);
        if(flag==true){
            return new CommonReturnType("success","注册成功");
        }
        else{
            return new CommonReturnType("fail","注册服务不可用");
        }

    }

    @RequestMapping(value = "/login",method = RequestMethod.POST, consumes = CONTENT_TYPE_FROMED)
    @ResponseBody
    public CommonReturnType login(@RequestParam("email") String email, @RequestParam("password") String password,HttpServletRequest request) throws BusinessException {

        if(email.equals("")||password.equals("")){
            throw new BusinessException(BusinessError.BUSINESS_ERROR);
        }

        UserModel user = userService.login(email,password);
        if(user==null){
            throw new BusinessException(BusinessError.WRONG_ACCOUNT_OR_PASSWORD);
        }
        HttpSession session = request.getSession();
        session.setAttribute("LOGINUSER",user);
        session.setAttribute("ISLOGIN",true);
        return new CommonReturnType("success",null);
    }


    private UserVO convertFromModel(UserModel userModel){
        if(userModel==null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }



}
