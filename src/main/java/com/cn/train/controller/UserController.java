package com.cn.train.controller;

import com.cn.train.entity.User;
import com.cn.train.service.UserService;
import com.cn.train.utils.CookieHelper;
import com.cn.train.utils.ReturnHelper;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RequestMapping(value = "user", method = {RequestMethod.GET,RequestMethod.POST})
@RestController
@Api(value = "user", description = "用户管理", basePath = "server")
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
    public Map<String, Object> userRegister(User user){
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if(null != user){
                map = userService.userRegister(user);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        return map;
    }

    @RequestMapping(value = "uploadpic")
    @ApiOperation(value = "上传图片信息", httpMethod = "POST", notes = "上传图片")
    public Map<String, Object> uploadPicture(@RequestParam("file") MultipartFile file) {
        Map<String, Object> map;
        String path = "/Users/kuangjh/Documents/HBuilderProjects/OnlineTrainingWebApi/img/headpic/";
        try {
            //判断file数组不能为空并且长度大于0
            if (file != null) {
                System.out.println(file.getOriginalFilename());
                MultipartFile mulfile = file;
                //保存文件
                map = userService.saveFile(mulfile, path);

            }else{
                map = ReturnHelper.fail("fail_null");
            }
        } catch (Exception e){
            System.out.println(e);
            return ReturnHelper.fail(e.toString());
        }
        return map;
    }


    @RequestMapping(value="deletepic")
    @ApiOperation(value = "删除图片信息", httpMethod = "POST", notes = "删除图片")
    public Map<String, Object> deletePicture(@RequestParam("filename") String filename){
        Map<String,Object> map;
        try{
            if(StringUtils.isNotBlank(filename)){
                map = userService.delFile(filename);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return ReturnHelper.fail((e.toString()));
        }
        return map;
    }

    @RequestMapping(value="updatemsg")
    @ApiOperation(value = "更新个人资料", httpMethod = "POST", notes = "更新资料")
    public Map<String, Object> updateUsermsg(HttpServletRequest request, User user){
        Map<String, Object> map;
        try{
            /**
             * 获取用户id
             */
            if(null == CookieHelper.getCookieValue(request, "uid")){
                map = ReturnHelper.fail("获取不到uid");
                return map;
            }
            int uid = Integer.parseInt(CookieHelper.getCookieValue(request, "uid"));

            if(null != user){
                user.setUid(uid);
                map = userService.updateUserMsg(user);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map = ReturnHelper.fail(e.toString());
        }

        return map;
    }

    @RequestMapping(value="getmsg")
    @ApiOperation(value = "获取用户信息", httpMethod = "POST", notes = "获取用户信息")
    public Map<String, Object> getUsermsg(HttpServletRequest request){
        Map<String, Object> map;
        try {
            /**
             * 获取用户id
             */
            if(null == CookieHelper.getCookieValue(request, "uid")){
                map = ReturnHelper.fail("获取不到uid");
                return map;
            }
            int uid = Integer.parseInt(CookieHelper.getCookieValue(request, "uid"));
            if(uid != 0){
                map = userService.getUserMsg(uid);
            }else {
                map = ReturnHelper.fail("参数校验失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            map = ReturnHelper.fail(e.toString());
        }

        return map;
    }
}
