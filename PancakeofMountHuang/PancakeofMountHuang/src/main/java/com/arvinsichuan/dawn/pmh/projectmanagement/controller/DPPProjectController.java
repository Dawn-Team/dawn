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

import com.arvinsichuan.dawn.pmh.datacleaning.DataPreProcessor;
import com.arvinsichuan.dawn.pmh.projectmanagement.exceptions.ConfigurationInvalidException;
import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectManager;
import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectService;
import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity generateADPPProject() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = dppProjectManager.generateAProject();
        webInfoEntity
                .isOK()
                .addInfoAndData("projectToken", dppProjectService.getUuid());
        return webInfoEntity;
    }


    @RequestMapping(value = "/{uuid}/alias", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity setProjectAlias(@PathVariable("uuid") UUID projectToken,
                                         @RequestParam(required = false) String alias) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        DPPProjectService dppProjectService = null;
        try {
            dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
            if (alias != null && !alias.isEmpty()) {
                dppProjectService.setProjectAlias(alias);
            }
            webInfoEntity
                    .isOK()
                    .addInfoAndData("projectToken", projectToken)
                    .addInfoAndData("alias", dppProjectService.getProjectAlias());
        } catch (EmptyDataException e) {
            e.printStackTrace();
            webInfoEntity.haveException(e);
        }
        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/cubLevelNames", method = RequestMethod.POST)
    public WebInfoEntity getCubeLevelNames(@PathVariable("uuid") UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        List<String> cubeLevelNames = null;
        try {
            DPPProjectService dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
            cubeLevelNames = dppProjectService.getCubeLevelNames();
            webInfoEntity
                    .isOK()
                    .addInfoAndData("projectToken", projectToken)
                    .addInfoAndData("cubeLevelNames", cubeLevelNames);
        } catch (ConfigurationInvalidException | EmptyDataException e) {
            e.printStackTrace();
            webInfoEntity.haveException(e);
        }
        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/squareLevelNames", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity getSquareLevelNames(@PathVariable("uuid") UUID projectToken, @RequestParam(required = false)
            String
            cubeLevelName) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        try {
            DPPProjectService dppProjectService = dppProjectManager.retrieveProjectByUUID(projectToken);
            webInfoEntity
                    .isOK()
                    .addInfoAndData("projectToken", projectToken)
                    .addInfoAndData("squareLevelNames", dppProjectService.getSquareLevelNames());
            if (cubeLevelName != null && !cubeLevelName.isEmpty()) {
                List squareLevelNames = dppProjectService.getSquareLevelNamesOfSheetName(cubeLevelName);
                webInfoEntity.addInfoAndData("squareLevelNames", squareLevelNames);
            }
        } catch (ConfigurationInvalidException | EmptyDataException e) {
            e.printStackTrace();
            webInfoEntity.haveException(e);
        }
        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/allProcessor", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity getAllProcessMethod(@PathVariable("uuid") UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();
        webInfoEntity
                .isOK()
                .addInfoAndData("allPreprocessor", DataPreProcessor.preProcessBeanMap.values());
        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/processMethod", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity setProcessMethod(@PathVariable("uuid") UUID projectToken,
                                          @RequestParam(required = false, value = "methodName") String methodName) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/commit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity commitMission(@PathVariable("uuid") UUID projectToken) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    @RequestMapping(value = "/{uuid}/status", method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public WebInfoEntity lookUpMissionStatus() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();


        return webInfoEntity;
    }
}
