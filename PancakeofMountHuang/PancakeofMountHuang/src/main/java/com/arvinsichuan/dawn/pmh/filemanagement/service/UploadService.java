package com.arvinsichuan.dawn.pmh.filemanagement.service;

import com.arvinsichuan.dawn.pmh.filemanagement.entity.FileUploadRecord;
import com.arvinsichuan.dawn.pmh.filemanagement.entity.StatusEnum;
import com.arvinsichuan.dawn.pmh.filemanagement.repository.FileUploadRecordsRepository;
import com.arvinsichuan.general.PmhConfigurations;
import com.arvinsichuan.general.auth.SecurityInfo;
import com.arvinsichuan.general.users.entity.User;
import com.arvinsichuan.general.users.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
    Author:Administrator
    Time:2017/11/1 10:01
*/
@Service("uploadService")
public class UploadService {

    @Resource
    private PmhConfigurations pmhConfigurations;

    @Resource(name = "uploadRepository")
    private FileUploadRecordsRepository fileUploadRecordsRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;


    public FileUploadRecord generateAnUploadRecord() {
        String username = SecurityInfo.getUsername();

        //创建一个上传记录
        FileUploadRecord fileUpload = new FileUploadRecord();


        fileUpload.setFileUuid(UUID.randomUUID());

        //设置上传状态为上传中
        fileUpload.setStatus(StatusEnum.UPLOADING);

        //设置当前上传时间
        fileUpload.setUploadDatetime(LocalDateTime.now());

        //设置存储用途
        fileUpload.setPurpose("Upload Data");

        //设置上传文件的用户名称
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("user = " + userRepository.findOne(username).getUsername());
        User user = userRepository.findOne(username);
        fileUpload.setUploadUser(user);


        return fileUpload;
    }

    public void saveUploadFileAndRecord(MultipartFile file, FileUploadRecord fileUploadRecord) throws IOException {
        //默认路径
        String defaultPath = pmhConfigurations.getUploadUrl();

        fileUploadRecord.setOriginalFilename(file.getOriginalFilename());
        fileUploadRecord.setExtensionName(getExtensionName(file.getOriginalFilename()));

        //最终文件存储路径
        String path = defaultPath + File.separator + fileUploadRecord.getFileUuid()
                + fileUploadRecord.getExtensionName();

        System.out.println("path = " + path);
        System.out.println("***************************************************************************");
        System.out.println("user = " + fileUploadRecord.getUploadUser().getUsername());
        System.out.println("***************************************************************************");
        System.out.println(fileUploadRecord.getFileUuid());

        //存储文件
        file.transferTo(new File(path));

        fileUploadRecord.setStatus(StatusEnum.NORMAL);


        User user = fileUploadRecord.getUploadUser();
        List fileUploadRecords = user.getFileUploadRecords();
        fileUploadRecords.add(fileUploadRecord);
        user.setFileUploadRecords(fileUploadRecords);


        userRepository.save(user);


    }

    private String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                filename = filename.substring(dot);
            }
        }
        return filename;
    }
}
