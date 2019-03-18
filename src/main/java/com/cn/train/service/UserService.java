package com.cn.train.service;

import java.util.Map;

public interface UserService {
    Map<String, Object> userLogin() throws Exception;

    Map<String, Object> userRegister() throws Exception;
}
