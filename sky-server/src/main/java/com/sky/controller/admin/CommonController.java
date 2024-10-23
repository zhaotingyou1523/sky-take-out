package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {
//    @Autowired
//    private AliOssUtil aliOssUtil;
//
//    @PostMapping("/upload")
//    @ApiOperation("文件上传")
//    public Result<String> upload(MultipartFile file) {
//        log.info("文件上传:{}",file);
//
//        try{
//            //原始文件名
//            String originalFilename = file.getOriginalFilename();
//            //街区原始文件名的后缀
//            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
//            //构造新文件名称
//            String objectName = UUID.randomUUID().toString() + extension;
//
//            //文件的请求路径
//            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
//            return Result.success(filePath);
//        }catch (Exception e){
//            log.error("文件上传失败{}",e);
//        }
//        return Result.error(MessageConstant.UPLOAD_FAILED);
//    }
}
