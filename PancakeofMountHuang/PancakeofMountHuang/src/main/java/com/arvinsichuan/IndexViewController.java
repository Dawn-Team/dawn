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

package com.arvinsichuan;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/9/28
 * <p>
 * Package: com.arvinsichuan
 * @author ArvinSiChuan
 */
@Controller
public class IndexViewController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String directToIndex(Model model) {
        setDefaultModel(model);
        setUserModel(model);
        return "index";
    }

    private void setUserModel(Model model) {
        //TODO make this configuration file available.
        model.addAttribute("userSignUpEnabled", true);
    }

    private void setDefaultModel(Model model) {
        model.addAttribute("web_name", "Dawn");
        model.addAttribute("copyright_years", LocalDate.now().getYear());
        model.addAttribute("copyright_entity","Dawn Team");
        model.addAttribute("mail_url", "service@mail.arvinsichuan.com");
        model.addAttribute("github_repository_name","dawn");
        model.addAttribute("github_repository_url","https://github.com/Dawn-Team/dawn");
    }
}
