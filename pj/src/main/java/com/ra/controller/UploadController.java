package com.ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@Controller
@PropertySource("classpath:config.properties")
public class UploadController {
    @Value("${path}")
    private String path;
    @Autowired
    ServletContext servletContext;

    @GetMapping("/upload")
    public String upload() {
        return "form-upload";
    }

    // cach 1: dung HttpServletRequest
    @PostMapping("/upload")
    public String postUpload(@RequestParam("img") MultipartFile file) {
        String fileName = file.getOriginalFilename();
//        cach 1:
//        String path = request.getServletContext().getRealPath("uploads/images/");
//        cach 2: dung autowired, khai bao servletContrext.getContextPath
//        String path = servletContext.getRealPath("uploads/images/");

//        dung trong du an cua minh
//        String path = "D:\\duc\\mySQL\\projectMD4\\pj\\src\\main\\webapp\\uploads\\images\\";

        File destination = new File(path + fileName);
        System.out.println("path" + path);
        System.out.println("des" + destination);
        try {
//            cach 1: dung transferTo
//            file.transferTo(destination);
//            cach 2:
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "form-upload";
    }


}
