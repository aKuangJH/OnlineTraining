package com.cn.train.service.impl;

import com.cn.train.dao.UserMapper;
import com.cn.train.entity.User;
import com.cn.train.service.UserService;
import com.cn.train.utils.CookieHelper;
import com.cn.train.utils.ReturnHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Map;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

    // 头像存储地址
    public static final String path = "/Users/kuangjh/Documents/HBuilderProjects/OnlineTrainingWebApi/img/headpic/";

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
                CookieHelper.setCookie(response,"uimg",user.getUimg(),-1);
                CookieHelper.setCookie(response,"role",user.getRole().toString(),-1);
            }
        }else {
            map = ReturnHelper.fail("登陆失败");
        }
        return map;
    }

    @Override
    public Map<String, Object> userRegister(User user) throws Exception {
        Map<String, Object> map;
        User existuser = userMapper.selectUserByName(user.getUsername());
        if(null != existuser){
            map = ReturnHelper.fail("已存在");
        }else {
            int result = userMapper.insertSelective(user);
            if(result > 0){
                map = ReturnHelper.success("success");
            }else {
                map = ReturnHelper.fail("fail");
            }
        }
        return map;
    }


    /***
     * 保存文件
     * @param file
     * @return
     */
    @Override
    public Map<String, Object> saveFile(MultipartFile file, String path) throws Exception {
        Map<String, Object> map;
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();

                String fileName = file.getOriginalFilename();
                String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                String randomName = Integer.toHexString(new Random().nextInt());

                String creatFileName = randomName +"."+ fileType;
                System.out.println("creatFileName===>"+creatFileName);

                // 文件保存路径
                //String savePath = path + file.getOriginalFilename();
                String savePath = path + creatFileName;
                // 转存文件
                file.transferTo(new File(savePath));
                map = ReturnHelper.success("success");
                map.put("creatFileName",creatFileName);

            } catch (Exception e) {
                System.out.println(e);
                return ReturnHelper.fail(e.toString());
            }
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }



    @Override
    public Map<String, Object> delFile(String filename) throws Exception {
        Map<String,Object> map;

        String filelocation = path + filename;

        File file = new File(filelocation);
        if(file.exists() && file.isFile()) {
            file.delete();
            map = ReturnHelper.success("delete success");
        }else {
            map = ReturnHelper.fail("delete fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> updateUserMsg(User user) throws Exception {
        Map<String,Object> map;
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result > 0){
            map = ReturnHelper.success("success");
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }

    @Override
    public Map<String, Object> getUserMsg(Integer uid) throws Exception {
        Map<String,Object> map;
        User user = userMapper.selectByPrimaryKey(uid);
        if(null != user){
            map = ReturnHelper.success("success");
            map.put("user",user);
        }else {
            map = ReturnHelper.fail("fail");
        }
        return map;
    }


}
