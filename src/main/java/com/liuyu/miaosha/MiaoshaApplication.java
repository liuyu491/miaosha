package com.liuyu.miaosha;

import com.liuyu.miaosha.dao.UserPasswordDOMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.liuyu.miaosha"})
@MapperScan("com.liuyu.miaosha.dao")
public class MiaoshaApplication {

@Autowired
UserPasswordDOMapper userPasswordDOMapper;

    public static void main(String[] args) {
        SpringApplication.run(MiaoshaApplication.class, args);
    }

}
