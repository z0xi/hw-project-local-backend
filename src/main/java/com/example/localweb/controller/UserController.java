package com.example.localweb.controller;
import com.alibaba.fastjson.JSONObject;
import com.example.localweb.entity.User;
import com.example.localweb.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    UserService service;
    @ResponseBody
    @RequestMapping(value = "/localRequestService",method = RequestMethod.POST)
        public String requestService(@RequestParam("objects") String obj) throws IOException, InterruptedException {
//        List<JSONObject> l = JSONObject.parseArray(objects,JSONObject.class);
//        System.out.print("debug");
//        JSONObject obj = new JSONObject();
//        for(JSONObject json : l){
//            obj.put(json.getString("name"),json.get("value"));
//        }
        // https://blog.csdn.net/yelllowcong/article/details/79711429
        String content = obj;
        String flag = "true";
        try {
            // 保证创建一个新文件
//            File file = new File("E://a.json");
            File file = new File("/home/kali/Desktop/hw-project/user/client_folder/to_v_attr.json");
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            file.createNewFile();
            // 将格式化后的字符串写入文件
            Writer write = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            write.write(content);
            write.flush();
            write.close();
            int status = service.callUser();
        } catch (Exception e) {
            flag = "false";
            e.printStackTrace();
        }
        return flag;
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
    @RequestMapping(value = "/localRequestUpload", method = RequestMethod.POST)
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
        try {
            //获取图片名称
//            String imgName = uploadFile.getOriginalFilename();
            String imgName = "credential.json";
            String path = "/home/kali/Desktop/hw-project/user/client_folder/"+imgName;

            //文件实现上传
            uploadFile.transferTo(new File(path));
            //调用./client
            int status = service.callClient();
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

}