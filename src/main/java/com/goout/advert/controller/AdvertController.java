package com.goout.advert.controller;

import com.goout.advert.entity.Advert;
import com.goout.advert.service.IAdvertService;
import com.goout.properties.Properties;
import com.goout.train.model.response.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/advert/",produces = "application/json;charset=UTF-8")
public class AdvertController {
    private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);

    @GetMapping(value = "/file")
    public String file() {
        return "file";
    }

    @Autowired
    private IAdvertService airService;
    @Autowired
    private Properties properties;


    @PostMapping("/insertAdvert")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse insertAdvet(@RequestParam(value = "userId",required = false) Integer userId, @RequestParam(value = "advertName",required = false) String advertName,@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        String fileName = file.getOriginalFilename();  // 文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = properties.getAdvertFolderPath(); // 上传后的路径
        fileName = UUID.randomUUID().toString().replace("-", "") + suffixName; // 新文件名
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Advert ad = new Advert();
        ad.setLocalUrl(dest.getAbsolutePath());
        ad.setName(advertName);
        ad.setShowAd(false);
        return RestResponse.succuess(airService.insertAdvert(ad));
    }


    @PostMapping("/getAdList")
    public RestResponse getAd(HttpServletRequest request) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        List<Advert> ads = airService.getAdvertList();
        for(Advert ad : ads){
            if(ad.getShowAd() != null && ad.getShowAd()){
                FileInputStream fileInputStream = null;
                try {
                    fileInputStream = new FileInputStream(ad.getLocalUrl());
                }catch (Exception e){
                    //文件不存在了
                    continue;
                }
                byte[] b = new byte[1024];
                int len = -1;
                while((len = fileInputStream.read(b)) != -1) {
                    bos.write(b, 0, len);
                }
                byte[] fileByte = bos.toByteArray();
                //以上为读取图片变成字节数组
                HashMap<String, Object> map = new HashMap<>(2);
                BASE64Encoder encoder = new BASE64Encoder();
                String data = encoder.encode(fileByte);
                ad.setImg(data);
            }

        }
        return RestResponse.succuess(ads);
    }

    @PostMapping("/showAd")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse showAdList(HttpServletRequest request,@RequestParam(value = "id",required = true) String id) throws Exception {
        try {
            return RestResponse.succuess(airService.showAdvert(id));
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.fail("false");
        }
    }

    @PostMapping("/hideAd")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse hideAd(HttpServletRequest request,@RequestParam(value = "id",required = true) String id) throws Exception {
        try {
            return RestResponse.succuess(airService.hideAdvert(id));
        }catch (Exception e){
            e.printStackTrace();
            return RestResponse.fail("false");
        }
    }



    @DeleteMapping("/deleteAdvert")
    @PreAuthorize("hasRole('ROLE_SUPER')")
    public RestResponse deleteAdvert(HttpServletRequest request,@RequestParam("id") Integer id) throws Exception {
        return RestResponse.succuess(airService.deleteAdvert(id));
    }

}
