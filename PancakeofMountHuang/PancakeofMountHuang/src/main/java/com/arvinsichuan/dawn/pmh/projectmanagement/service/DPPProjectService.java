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

package com.arvinsichuan.dawn.pmh.projectmanagement.service;

import com.arvinsichuan.dawn.pmh.datasource.DPPDatasource;
import com.arvinsichuan.dawn.pmh.datasource.DataSourceCube;
import com.arvinsichuan.dawn.pmh.datasource.ExcelDPPDataSourceImpl;
import com.arvinsichuan.dawn.pmh.datasource.entities.DSTypesEnum;
import com.arvinsichuan.dawn.pmh.datasource.entities.DatasourceEntity;
import com.arvinsichuan.dawn.pmh.datasource.exceptions.DataSourceTypeInvalidException;
import com.arvinsichuan.dawn.pmh.datasource.exceptions.ParametersNotFoundException;
import com.arvinsichuan.dawn.pmh.projectmanagement.entity.DPPProjectEntity;
import com.arvinsichuan.dawn.pmh.projectmanagement.exceptions.ConfigurationInvalidException;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import com.arvinsichuan.general.scheduleplanner.exceptions.InvalidAtomMissionInstanceException;
import com.arvinsichuan.general.scheduleplanner.exceptions.PriorityStrategyInvalidException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 01-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.projectmanagement.service
 *
 * @author ArvinSiChuan
 */
public class DPPProjectService {
    private DataSourceCube dataSourceCube;
    private DatasourceEntity datasourceEntity;
    private DPPProjectEntity dppProjectEntity = new DPPProjectEntity();
    private UUID uuid;


    @Resource(name = "excelDPPDataSource")
    private DPPDatasource dppDatasource;


    public DPPProjectService() {
    }


    public DPPProjectService(UUID uuid) {
        this.uuid = uuid;
    }


    public DPPProjectService setProjectAlias(String alias) {
        dppProjectEntity.setAlias(alias);
        return this;
    }

    public DPPProjectService setDataSourceEntity(DatasourceEntity dataSourceEntity) throws
            DataSourceTypeInvalidException, InvalidFormatException, IOException, ParametersNotFoundException {
        this.datasourceEntity = dataSourceEntity;
        if (dataSourceEntity.getType() != DSTypesEnum.EXCEL_FILE) {
            throw new DataSourceTypeInvalidException(dataSourceEntity.getType().toString());
        } else {
            Map<String, String> parameters = new TreeMap<>();
            parameters.put(ExcelDPPDataSourceImpl.EXCEL_FILE_PATH_PARA_NAME, dataSourceEntity.getLocation());
            dataSourceCube = dppDatasource.getDatasourceCubeWithParameters(parameters);
        }
        return this;
    }


    public List<String> getCubeLevelNames() throws ConfigurationInvalidException, EmptyDataException {
        testConfigurationAndData();
        return dataSourceCube.getCubeLevelNames();
    }

    public Map<String, List> getSquareLevelNames() throws EmptyDataException, ConfigurationInvalidException {
        testConfigurationAndData();
        return dataSourceCube.getSquareLevelNames();
    }

    public List getSquareLevelNamesOfSheetName(String cubeLevelName) throws ConfigurationInvalidException,
            EmptyDataException {
        return getSquareLevelNames().get(cubeLevelName);
    }


    private void testConfigurationAndData() throws ConfigurationInvalidException, EmptyDataException {
        if (datasourceEntity == null) {
            throw new ConfigurationInvalidException("Datasource Not Configured.");
        } else if (dataSourceCube == null) {
            throw new EmptyDataException("DatasourceCube Invalid");
        } else if (dppProjectEntity == null) {
            throw new ConfigurationInvalidException("Project Entity Initialization Error.");
        }
    }

    public void submitMission() throws PriorityStrategyInvalidException, InvalidAtomMissionInstanceException {
        // TODO config and submit here.
    }


    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }
}
