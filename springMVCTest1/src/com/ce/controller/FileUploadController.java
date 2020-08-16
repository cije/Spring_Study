package com.ce.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {
    /**
     * 传统方式
     */
    @RequestMapping("/upload1")
    public String fileUpload(HttpServletRequest request) throws Exception {
        System.out.println("传统方式文件上传");

        // 使用fileupload组件完成文件上传
        // 上传的位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断该路径是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        //解析request对象，获取上传文件项
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 解析request
        List<FileItem> items = upload.parseRequest(request);
        // 遍历
        for (FileItem item : items) {
            // 判断当前item对象是否是一个上传文件项
            if (item.isFormField()) {
                // 普通的表单项
            } else {
                // 上传文件项
                // 获取上传文件名称
                String fileName = item.getName();
                // 把文件的名称设置唯一值
                String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName = uuid + "_" + fileName;
                System.out.println(fileName);
                // 完成文件上传
                item.write(new File(realPath, fileName));
                // 删除临时文件
                item.delete();
            }
        }
        return "uploadSuccess";
    }

    /**
     * springMVC方式
     */
    @RequestMapping("/upload2")
    public String fileUpload2(HttpServletRequest request, MultipartFile upload) throws Exception {
        System.out.println("springMVC文件上传");

        // 上传的位置
        String realPath = request.getSession().getServletContext().getRealPath("/uploads/");
        // 判断该路径是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 上传文件项
        // 获取上传文件名称
        String fileName = upload.getOriginalFilename();
        // 把文件的名称设置唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;
        // 完成文件上传
        upload.transferTo(new File(realPath, fileName));
        return "uploadSuccess";
    }

    /**
     * 跨服务器方式
     */
    @RequestMapping("/upload3")
    public String fileUpload3(HttpServletRequest request,MultipartFile upload) throws Exception {
        System.out.println("跨服务器文件上传");

        // 定义上传文件服务器路径
        String path = "http://localhost:8081/fileuploadserver/uploads/";

        // 上传文件项
        // 获取上传文件名称
        String fileName = upload.getOriginalFilename();
        // 把文件的名称设置唯一值
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid + "_" + fileName;

        // 创建客户端的对象
        Client client = Client.create();

        // 和图片服务器进行连接
        WebResource webResource = client.resource(path + fileName);

        // 上传文件
        webResource.put(upload.getBytes());
        return "uploadSuccess";
    }
}
