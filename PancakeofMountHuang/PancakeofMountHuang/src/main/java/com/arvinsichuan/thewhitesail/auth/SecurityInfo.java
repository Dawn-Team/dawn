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



import com.arvinsichuan.thewhitesail.users.entity.AuthoritiesEnum;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/1
 * <p>
 * Package: com.arvinsichuan.auth
 * @author ArvinSiChuan
 */
public class SecurityInfo {

    public static Principal getPrincipal() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Principal) {
            return ((Principal) principal);
        }
        return null;
    }

    public static UserDetails getUserDetails() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal);
        }
        return null;
    }


    static AuthoritiesEnum getTopAuth() {
        Iterator iterator = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator();
        List<AuthoritiesEnum> authorities = new ArrayList<>();
        while (iterator.hasNext()) {
            authorities.add(AuthoritiesEnum.valueOf(iterator.next().toString()));
        }
        authorities = authorities.stream().sorted().collect(Collectors.toList());
        return authorities.get(0);
    }
}
