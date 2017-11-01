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

package com.arvinsichuan.dawn.pmh.datasource.entities;

import com.arvinsichuan.general.users.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 01-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datasource.entities
 *
 * @author ArvinSiChuan
 */
@Entity
@Table(name = "Datasources")
public class DatasourceEntity implements Serializable {

    private static final long serialVersionUID = -6617522274467852481L;
    @Id
    @GeneratedValue
    @Column(name = "datasource_uuid", nullable = false)
    private UUID dsUuid;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DSTypesEnum type;

    @Column(name = "stage", nullable = false)
    @Enumerated(EnumType.STRING)
    private DSStageEnum stage;

    @Column(name = "location", length = 255, nullable = false)
    private String location;

    @Column(name = "status",nullable = false)
    @Enumerated(EnumType.STRING)
    private DSStatusEnum status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_datasources_users"))
    private User relatedUser;


    public DatasourceEntity(){}

    public DatasourceEntity(DSTypesEnum type, DSStageEnum stage, String location, DSStatusEnum status, User
            relatedUser) {
        this.type = type;
        this.stage = stage;
        this.location = location;
        this.status = status;
        this.relatedUser = relatedUser;
    }

    public UUID getDsUuid() {
        return dsUuid;
    }

    public void setDsUuid(UUID dsUuid) {
        this.dsUuid = dsUuid;
    }

    public DSTypesEnum getType() {
        return type;
    }

    public void setType(DSTypesEnum type) {
        this.type = type;
    }

    public DSStageEnum getStage() {
        return stage;
    }

    public void setStage(DSStageEnum stage) {
        this.stage = stage;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public DSStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DSStatusEnum status) {
        this.status = status;
    }

    public User getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(User relatedUser) {
        this.relatedUser = relatedUser;
    }
}
