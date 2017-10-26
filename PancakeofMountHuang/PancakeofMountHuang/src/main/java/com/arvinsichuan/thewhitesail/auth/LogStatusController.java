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

package com.arvinsichuan.thewhitesail.auth;

import com.arvinsichuan.general.WebTransmissionInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/1
 * <p>
 * Package: com.arvinsichuan.auth
 */
@RestController
@RequestMapping(path = "/auth")
public class LogStatusController {

    @RequestMapping(path = "/status", method = RequestMethod.POST)
    public WebTransmissionInfo getLoginStatus() {
        WebTransmissionInfo info = new WebTransmissionInfo();
        info.put("loginStatus", SecurityInfo.getTopAuth());
        info.ok();
        info.put("roleCode", SecurityInfo.getTopAuth().ordinal());
        return info;
    }

    @RequestMapping(path = "/login",method = RequestMethod.GET)
    public Serializable loginPageInfo(){
        WebTransmissionInfo info = getLoginStatus();
        info.put("login page","/auth/login");
        return info;
    }
}
