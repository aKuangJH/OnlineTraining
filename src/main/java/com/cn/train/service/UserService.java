package com.cn.train.service;

import com.cn.train.entity.User;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService {
    Map<String, Object> userLogin(HttpServletResponse response, String username, String password) throws Exception;

    Map<String, Object> userRegister(User user) throws Exception;

    Map<String, Object> saveFile(MultipartFile file, String path) throws Exception;

    Map<String, Object> delFile(String filename) throws Exception;

    Map<String, Object> updateUserMsg(User user) throws Exception;

    Map<String, Object> getUserMsg(Integer uid) throws Exception;
}
