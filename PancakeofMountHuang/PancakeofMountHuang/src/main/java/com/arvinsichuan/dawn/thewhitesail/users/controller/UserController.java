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

package com.arvinsichuan.dawn.thewhitesail.users.controller;



import com.arvinsichuan.dawn.general.WebTransmissionInfo;
import com.arvinsichuan.dawn.thewhitesail.users.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/9/28
 * <p>
 * Package: com.arvinsichuan.users.controller
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Resource(name = "userService")
    private
    UserService userService;

    @RequestMapping(path = "/signUp", method = RequestMethod.POST)
    @Transactional
    public WebTransmissionInfo signUp(@RequestParam(value = "username") String name, @RequestParam(value =
            "password") String password) {
        return userService.userSignUp(name,password);
    }

}
