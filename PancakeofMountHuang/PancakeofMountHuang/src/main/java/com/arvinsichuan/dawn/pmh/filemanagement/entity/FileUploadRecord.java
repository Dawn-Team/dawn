package com.arvinsichuan.dawn.pmh.filemanagement.entity;

import com.arvinsichuan.thewhitesail.users.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/*
    Author:Administrator
    Time:2017/10/31 15:04
*/
@Entity
@Table(name = "File_Upload")
public class FileUploadRecord implements Serializable {
    private static final long serialVersionUID = -580630154682154540L;
    @Id
    @Column(name = "File_UUID",nullable = false)
    @GeneratedValue
    private UUID fileUuid;

    @Column(name = "Upload_datetime",nullable = false)
    private LocalDateTime uploadDatetime;

    @Column(name = "Original_filename",nullable = false)
    private String originalFilename;

    @Column(name = "Extension_name",nullable = false)
    private String extensionName;

    @Column(name = "Purpose",nullable = false)
    private String purpose;

    @Column(name = "Status",nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnum status;


    @ManyToOne(optional = false,fetch = FetchType.LAZY)
    @JoinColumn(name = "upload_user", insertable = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_upload_users"))
    @JsonBackReference
    private User uploadUser;

    public FileUploadRecord() {
        fileUuid = UUID.randomUUID();
    }



    public LocalDateTime getUploadDatetime() {
        return uploadDatetime;
    }

    public void setUploadDatetime(LocalDateTime uploadDatetime) {
        this.uploadDatetime = uploadDatetime;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getExtensionName() {
        return extensionName;
    }

    public void setExtensionName(String extensionName) {
        this.extensionName = extensionName;
    }

    public UUID getFileUuid() {
        return fileUuid;
    }

    public void setFileUuid(UUID fileUuid) {
        this.fileUuid = fileUuid;
    }

    public String getPurpose() {
        return purpose;

    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public User getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(User uploadUser) {
        this.uploadUser = uploadUser;
    }
}
