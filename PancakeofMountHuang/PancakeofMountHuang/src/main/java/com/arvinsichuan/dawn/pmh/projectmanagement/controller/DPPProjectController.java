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

import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectManager;
import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectService;
import com.arvinsichuan.general.WebInfoEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
                .addInfoAndData("projectToken", dppProjectService.getUuid().toString());
        return webInfoEntity;
    }


    public WebInfoEntity getDatasourceList() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    public WebInfoEntity setProjectAlias(String alias) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    public WebInfoEntity getCubeLevelNames() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    public WebInfoEntity getSquareLevelNames(String cubeLevelName) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }

    public WebInfoEntity getAllProcessMethod() {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }


    public WebInfoEntity setProcessMethod(String methodName) {
        WebInfoEntity webInfoEntity = new WebInfoEntity();

        return webInfoEntity;
    }


}
