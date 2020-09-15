package com.funtl.my.shop.web.admin.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传控制器
 * @author: TOLUNGKIT
 * @version: 1.0.0
 * @date: 2020-09-14 09:18
 **/
@Controller
public class UploadController {

    private static final String UPLOAD_PATH = "/static/upload/";

    /**
     * 文件上传 （异步，JSON）
     * @param dropzFile Dropzone
     * @param editorFile wangEditor
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public Map<String, Object> upload(MultipartFile dropzFile, MultipartFile editorFile, HttpServletRequest request){
        Map<String, Object> result = new HashMap<>();

        //前端上传的文件
        MultipartFile myFile = dropzFile == null ? editorFile : dropzFile;

        // 文件后缀
        String fileName = myFile.getOriginalFilename();
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        //文件存放路径
        String filePath = request.getSession().getServletContext().getRealPath(UPLOAD_PATH);
        // 判断路径是否存在，不存在则创建文件夹
        File file = new File(filePath);
        if(!file.exists()){
            file.mkdir();
        }
        // 将文件写入目标目录
        file = new File(filePath, UUID.randomUUID() + fileSuffix);
        try {
            myFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
        }

        // dropzone 上传
        if(dropzFile != null){
            result.put("fileName", UPLOAD_PATH + file.getName());
        }

        // wangEditor 上传
        else {
            /**
             * scheme： 服务器提供的协议 http/https
             * serverName：服务器名称 localhost/ip/domain
             * serverPort: 服务器端口
             */
            String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            result.put("errno",0);
            result.put("data",new String[]{serverPath + UPLOAD_PATH +file.getName()});
        }
        return result;
    }
}
