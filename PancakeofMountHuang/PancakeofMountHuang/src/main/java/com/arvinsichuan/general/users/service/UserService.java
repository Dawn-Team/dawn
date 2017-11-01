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

package com.arvinsichuan.general.users.service;


import com.arvinsichuan.general.WebInfoEntity;
import com.arvinsichuan.general.exceptions.DuplicatedDataException;
import com.arvinsichuan.general.users.entity.AuthoritiesEnum;
import com.arvinsichuan.general.users.entity.Authority;
import com.arvinsichuan.general.users.entity.User;
import com.arvinsichuan.general.users.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/2
 * <p>
 * Package: com.arvinsichuan.users
 */
@Service("userService")
public class UserService {

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @Resource(name = "passEncoder")
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(rollbackFor = DuplicatedDataException.class)
    public WebInfoEntity userSignUp(String username, String password) throws DuplicatedDataException{
        WebInfoEntity webInfo = new WebInfoEntity();
        User userInDB = userRepository.findOne(username);
        if (userInDB != null && userInDB.getUsername().equals(username)) {
           throw new DuplicatedDataException("User.username value: "+username);
        } else {
//          Process username to lowercase
            username = username.toLowerCase();

//          Encode passwords
            password = passwordEncoder.encode(password);

//          Build an user instance
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEnabled(true);

//          Assign Authorities
            Authority authority = new Authority();
            authority.setUserByUsername(user);
            authority.setAuthority(AuthoritiesEnum.ROLE_USER);
            List<Authority> authorities = new ArrayList<>();
            authorities.add(authority);
            user.setAuthorities(authorities);

            try {
                userRepository.save(user);
                user.setPassword("[PROTECTED]");
                webInfo.isOK();
                webInfo.addInfoAndData("user", user);
            } catch (Exception e) {
                e.printStackTrace();
                webInfo.haveException(e, "Exception in saving");
            }
        }
        return webInfo;
    }
}
