/*
 *     This project is part of project Dawn, A Data Process Solution.
 *     Copyright (C) 2017, Dawn team<https://github.com/Dawn-Team>.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.arvinsichuan.dawn.pmh.datasource.services;

import com.arvinsichuan.dawn.pmh.datasource.entities.DSStageEnum;
import com.arvinsichuan.dawn.pmh.datasource.entities.DSStatusEnum;
import com.arvinsichuan.dawn.pmh.datasource.entities.DSTypesEnum;
import com.arvinsichuan.dawn.pmh.datasource.entities.DatasourceEntity;
import com.arvinsichuan.dawn.pmh.datasource.repositories.DatasourceRepository;
import com.arvinsichuan.dawn.pmh.filemanagement.entity.FileUploadRecord;
import com.arvinsichuan.general.auth.SecurityInfo;
import com.arvinsichuan.general.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 01-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datasource.services
 */
@Service("datasourceService")
public class DatasourceService {

    @Resource(name = "datasourceRepo")
    private DatasourceRepository datasourceRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public List<DatasourceEntity> getAllDatasource() {
        return datasourceRepository.findAllByRelatedUser(userRepository.findOne(SecurityInfo.getUsername()));
    }

    public DatasourceEntity addAnUploadedExcelToDatasource(FileUploadRecord record) {
        DatasourceEntity entity = new DatasourceEntity();
        entity.setDsUuid(UUID.randomUUID());
        entity.setRelatedUser(userRepository.findOne(SecurityInfo.getUsername()));
        entity.setStage(DSStageEnum.PRE_PROCESS);
        entity.setStatus(DSStatusEnum.NORMAL);
        entity.setType(DSTypesEnum.EXCEL_FILE);
        entity.setLocation(record.getFileUuid().toString());
        return datasourceRepository.save(entity);
    }

}
