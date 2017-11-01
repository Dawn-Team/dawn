package com.arvinsichuan.dawn.pmh.filemanagement.controller;

import com.arvinsichuan.dawn.pmh.filemanagement.entity.FileUploadRecord;
import com.arvinsichuan.dawn.pmh.filemanagement.service.UploadService;
import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import com.arvinsichuan.general.lockretrytool.RetryOnOptimisticLockingFailure;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/*
    Author:Administrator
    Time:2017/10/30 18:19
*/
@RestController
@RequestMapping("/fileUpload")
public class FileUploadController {

    @Resource(name = "uploadService")
    private UploadService uploadService;


    @RequestMapping(value = "/upload", method = RequestMethod.PUT)
    @RetryOnOptimisticLockingFailure
    public WebInfoEntity upload(@RequestParam(value = "myfiles") MultipartFile file,
                                HttpServletRequest request){
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        if (file == null || file.isEmpty()) {
            webInfoEntity.haveException(new EmptyDataException("upload file can't be null or empty"));
        } else {
            FileUploadRecord fileUploadRecord = uploadService.generateAnUploadRecord();
            try {
                uploadService.saveUploadFileAndRecord(file, fileUploadRecord);
                webInfoEntity
                        .isOK()
                        .addInfoAndData("uploadRecord",fileUploadRecord);
            } catch (IOException e) {
                e.printStackTrace();
                webInfoEntity.haveException(e);
            }
        }
        return webInfoEntity;
    }

}
