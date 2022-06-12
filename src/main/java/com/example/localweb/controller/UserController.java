package com.example.localweb.controller;
import com.example.localweb.entity.User;
import com.example.localweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class UserController {
    @Autowired
    UserService service;
    @RequestMapping(value = "/getUserItem",method = RequestMethod.GET)
    public String getUserItem(){
        User user = service.getUserInfo();
        return user.toString();
    }
    @RequestMapping(value = "/callClient",method = RequestMethod.GET)
    public int callClient() throws IOException, InterruptedException {
        int status = service.callClient();
        return status;
    }
    @RequestMapping(value = "/callUser",method = RequestMethod.GET)
    public int callUser() throws IOException, InterruptedException {
        int status = service.callUser();
        return status;
    }
    @RequestMapping(value = "/picupload", method = RequestMethod.POST)
    public String upload(@RequestParam("credential") MultipartFile uploadFile) throws IOException {
        if (null == uploadFile) {
            return "false";
        }
        // BMP、JPG、JPEG、PNG、GIF
        String fileName = uploadFile.getOriginalFilename().toLowerCase();
//        if (!fileName.endsWith(".md")){
//            return "false";
//        }
        //逻辑处理
        //https://blog.csdn.net/qq_39038793/article/details/102857003?spm=1001.2101.3001.6650.3&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-102857003-blog-101718878.pc_relevant_blogantidownloadv1&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7ECTRLIST%7Edefault-3-102857003-blog-101718878.pc_relevant_blogantidownloadv1&utm_relevant_index=6
        File fileDir = new File("/home/kali/Desktop/test/client_folder\n");
        if(!fileDir.exists()) {
            //如果没有目录应该创建目录
            fileDir.mkdirs();
        }
        try {
            //获取图片名称
//            String imgName = uploadFile.getOriginalFilename();
            String imgName = "credential.json";
            String path = "/home/kali/Desktop/test/client_folder/"+imgName;

            //文件实现上传q
            uploadFile.transferTo(new File(path));
            int status = service.callClient();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

}