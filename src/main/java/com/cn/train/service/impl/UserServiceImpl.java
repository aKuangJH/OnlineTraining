package com.cn.train.service.impl;

import com.cn.train.dao.UserMapper;
import com.cn.train.entity.User;
import com.cn.train.service.UserService;
import com.cn.train.utils.CookieHelper;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public Map<String, Object> userLogin(HttpServletResponse response, String username, String password) throws Exception {
        Map<String, Object> map;
        User user = userMapper.selectUserByNameAndPsw(username,password);
        if(user != null){
            map = ReturnHelper.success("登陆成功");
            if(user.getUid() != null){
                CookieHelper.setCookie(response,"uid",user.getUid().toString(),-1);
                CookieHelper.setCookie(response,"username",user.getUsername(),-1);
            }
        }else {
            map = ReturnHelper.fail("登陆失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> userRegister() throws Exception {
        return null;
    }
}
