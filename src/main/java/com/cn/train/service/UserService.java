package com.cn.train.service;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {
    Map<String, Object> userLogin(HttpServletResponse response, String username, String password) throws Exception;

    Map<String, Object> userRegister() throws Exception;
}
