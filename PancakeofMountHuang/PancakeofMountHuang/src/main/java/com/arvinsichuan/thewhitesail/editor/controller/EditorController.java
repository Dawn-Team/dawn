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

package com.arvinsichuan.thewhitesail.editor.controller;

import com.arvinsichuan.general.WebInfoEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/9/28
 * <p>
 * Package: com.arvinsichuan.editor.controller
 */
@RestController
@RequestMapping(value = "/editor")
public class EditorController {

    @RequestMapping(value = "/token",method = RequestMethod.POST)
    @PreAuthorize("hasRole('ROLE_USER')")
    public Serializable getGitHubAPIToken(){
        WebInfoEntity webInfoEntity=new WebInfoEntity();
        webInfoEntity.isOK();
        // TODO hard code to eliminate.
        webInfoEntity.addInfoAndData("Authorization","token 5aa8fddbb4f017835bc1042814d7d18d2a4e7829");
        return webInfoEntity;
    }

}
