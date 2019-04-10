package com.cn.train.controller;

import com.cn.train.entity.User;
import com.cn.train.service.UserService;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "user", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "user", description = "用户管理")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "login")
    @ApiOperation(value = "登录", httpMethod = "POST", notes = "用户登陆")
    public Map<String, Object> userLogin(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)){
                map = userService.userLogin(response, username, password);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return map;
    }


    @RequestMapping(value = "register")
    @ApiOperation(value = "用户注册", httpMethod = "POST", notes = "注册")
    public Map<String, Object> userRegister(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password,@RequestParam(value = "email") String email){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(email)){
                User user = new User();

//                map = userService.userRegister();
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }
}
