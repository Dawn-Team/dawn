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

package com.arvinsichuan.general.users.entity;

import com.arvinsichuan.dawn.pmh.filemanagement.entity.FileUploadRecord;
import com.arvinsichuan.thewhitesail.articles.entity.Article;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/1
 * <p>
 * Package: com.arvinsichuan.users.entity
 * @author ArvinSiChuan
 */
@Entity
@Table( name = "users")
public class User implements Serializable {

    private static final long serialVersionUID = 4683637038104129460L;

    @Id
    @Column(name = "username", length = 16, nullable = false)
    private String username;

    @Column(name = "password", length = 60, nullable = false)
    private String password;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @OneToMany(mappedBy = "userByUsername", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Authority> authorities;

    @OneToMany(mappedBy = "articleUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Article> articles;

    @OneToMany(mappedBy = "uploadUser", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<FileUploadRecord> fileUploadRecords;


    public User() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<FileUploadRecord> getFileUploadRecords() {
        return fileUploadRecords;
    }

    public void setFileUploadRecords(List<FileUploadRecord> fileUploadRecords) {
        this.fileUploadRecords = fileUploadRecords;
    }

    public User(String username, String password, Boolean enabled, List<Authority> authorities, List<Article>
            articles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.authorities = authorities;
        this.articles = articles;
    }
}
