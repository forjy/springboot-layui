package com.justryoo.manager.controller.system;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
import java.util.UUID;
@Controller
@RequestMapping("/images")
@ConfigurationProperties(prefix = "ckeditor")
public class ImageUploadController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    private String imageFilePath;
    private String applicationPath;

    public String getImageFilePath() {
        return imageFilePath;
    }

    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }

    public String getApplicationPath() {
        return applicationPath;
    }

    public void setApplicationPath(String applicationPath) {
        this.applicationPath = applicationPath;
    }

    /**
     * 上传图片
     *
     * @param file
     * @return
     */
    @RequestMapping("/ckeditorUpload")
    public void ckeditorUpload(@RequestParam("upload") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("开始上传图片");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + suffixName;
        log.info("上传文件文件名称：{}",newFileName);
        log.info("上传文件大小 ：{}",file.getSize());
        String uploadPath0 = request.getSession().getServletContext().getRealPath("/");
        String uploadPath1 = request.getSession().getServletContext().getRealPath("/"+imageFilePath);
        String uploadPath = request.getSession().getServletContext().getRealPath("/"+imageFilePath);
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(uploadPath +"/" + newFileName));

//        ImageUploadVo imageUploadVo = new ImageUploadVo();
//        imageUploadVo.setUploaded(1);
//        imageUploadVo.setFileName(newFileName);
//        imageUploadVo.setUrl("ddddddfsdfdsfd");
        String imageContextPath = request.getContextPath() + "/" + uploadPath + "/" + newFileName;
        response.setContentType("text/html;charset=UTF-8");
        String callback = request.getParameter("CKEditorFuncNum");
        PrintWriter out = response.getWriter();
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imageContextPath + "',''" + ")");
        out.println("</script>");
        out.flush();
        out.close();
    }
}
