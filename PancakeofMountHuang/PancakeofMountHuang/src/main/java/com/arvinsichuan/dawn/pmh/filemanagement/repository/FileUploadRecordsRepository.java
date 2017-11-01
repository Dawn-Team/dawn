package com.arvinsichuan.dawn.pmh.filemanagement.repository;

import com.arvinsichuan.dawn.pmh.filemanagement.entity.FileUploadRecord;
import com.arvinsichuan.general.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/*
    Author:Administrator
    Time:2017/10/31 20:42
*/
@Repository("uploadRepository")
public interface FileUploadRecordsRepository extends JpaRepository<FileUploadRecord,UUID> {
    List<FileUploadRecord> findByUploadDatetimeAndUploadUser(LocalDateTime dateTime, User user);
}
