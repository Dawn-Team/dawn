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

package com.arvinsichuan.dawn.pmh.projectmanagement.controller;

import com.arvinsichuan.dawn.pmh.datasource.entities.DatasourceEntity;
import com.arvinsichuan.dawn.pmh.projectmanagement.exceptions.ConfigurationInvalidException;
import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectManager;
import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectService;
import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
 * Package: com.arvinsichuan.dawn.pmh.projectmanagement.controller
 *
 * @author ArvinSiChuan
 */
@RestController
@RequestMapping("/dataPreProcess")
public class DPPProjectController {

    @Resource(name = "DPPProjectManager")
    private DPPProjectManager dppProjectManager;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public WebInfoEntity generateADPPProject() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = dppProjectManager.generateAProject();
        webInfoEntity
                .isOK()
                .addInfoAndData("projectToken", dppProjectService.getUuid());
        return webInfoEntity;
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public WebInfoEntity getDatasourceList(UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        List<DatasourceEntity> dataSourceList = dppProjectManager.getDataSourceList();
        webInfoEntity
                .isOK()
                .addInfoAndData("projectToken",projectToken)
                .addInfoAndData("DSList",dataSourceList);
        return webInfoEntity;
    }

    @RequestMapping(value = "/setAlias", method = RequestMethod.POST)
    public WebInfoEntity setProjectAlias(String alias,UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
        dppProjectService.setProjectAlias(alias);
        webInfoEntity
                .isOK()
                .addInfoAndData("projectToken",projectToken);
        return webInfoEntity;
    }

    @RequestMapping(value = "/getCubLevelNames", method = RequestMethod.POST)
    public WebInfoEntity getCubeLevelNames(UUID projectToken)  {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
        List<String> cubeLevelNames = null;
        try {
            cubeLevelNames = dppProjectService.getCubeLevelNames();
            webInfoEntity
                    .isOK()
                    .addInfoAndData("projectToken",projectToken)
                    .addInfoAndData("cubeLevelNames",cubeLevelNames);
        } catch (ConfigurationInvalidException | EmptyDataException e) {
            e.printStackTrace();
            webInfoEntity.haveException(e);
        }
        return webInfoEntity;
    }

    @RequestMapping(value = "/getSquareLevelNames", method = RequestMethod.POST)
    public WebInfoEntity getSquareLevelNames(String cubeLevelName,UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
        try {
            List squareLevelNames =dppProjectService.getSquareLevelNamesOfSheetName(cubeLevelName);
            webInfoEntity
                    .isOK()
                    .addInfoAndData("projectToken",projectToken)
                    .addInfoAndData("squareLevelNames",squareLevelNames);
        } catch (ConfigurationInvalidException | EmptyDataException e) {
            e.printStackTrace();
            webInfoEntity.haveException(e);
        }
        return webInfoEntity;
    }

    @RequestMapping(value = "/getAllProcess", method = RequestMethod.POST)
    public WebInfoEntity getAllProcessMethod(UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    @RequestMapping(value = "/setProcessMethod", method = RequestMethod.POST)
    public WebInfoEntity setProcessMethod(String methodName,UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    @RequestMapping(value = "/commitMission", method = RequestMethod.POST)
    public WebInfoEntity commitMission(UUID projectToken){
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }
}
