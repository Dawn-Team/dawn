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

package com.arvinsichuan.dawn.pmh.projectmanagement.entity;

import com.arvinsichuan.dawn.pmh.datasource.entities.DatasourceEntity;
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
 * Package: com.arvinsichuan.dawn.pmh.projectmanagement.entity
 *
 * @author ArvinSiChuan
 */
@Entity
@Table(name = "Data_Preprocess_Projects")
public class DPPProjectEntity implements Serializable {
    private static final long serialVersionUID = 5956126040219903946L;
    @Id
    @GeneratedValue
    @Column(name = "project_uuid", nullable = false)
    private UUID processUuid;

    @Column(name = "project_alias", length = 64)
    private String alias;

    @Column(name = "progress", nullable = false)
    private int progress = 0;

    @Column(name = "status_description", length = 128, nullable = false)
    private String statusDesc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "datasource", insertable = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_dpp_projects_datasources"))
    private DatasourceEntity datasource;

    @Column(name = "datasource_desc", length = 64)
    private String datasourceDesc;

    @Column(name = "method",length = 64,nullable = false)
    private String processMethod;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", insertable = true, nullable = false,
            foreignKey = @ForeignKey(name = "fk_DPPProjects_users"))
    private User relatedUser;

    public DPPProjectEntity() {
    }

    public DPPProjectEntity(String alias, int progress, String statusDesc, DatasourceEntity datasource, String
            datasourceDesc, String processMethod, User relatedUser) {
        this.alias = alias;
        this.progress = progress;
        this.statusDesc = statusDesc;
        this.datasource = datasource;
        this.datasourceDesc = datasourceDesc;
        this.processMethod = processMethod;
        this.relatedUser = relatedUser;
    }

    public UUID getProcessUuid() {
        return processUuid;
    }

    public void setProcessUuid(UUID processUuid) {
        this.processUuid = processUuid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public DatasourceEntity getDatasource() {
        return datasource;
    }

    public void setDatasource(DatasourceEntity datasource) {
        this.datasource = datasource;
    }

    public String getDatasourceDesc() {
        return datasourceDesc;
    }

    public void setDatasourceDesc(String datasourceDesc) {
        this.datasourceDesc = datasourceDesc;
    }

    public String getProcessMethod() {
        return processMethod;
    }

    public void setProcessMethod(String processMethod) {
        this.processMethod = processMethod;
    }

    public User getRelatedUser() {
        return relatedUser;
    }

    public void setRelatedUser(User relatedUser) {
        this.relatedUser = relatedUser;
    }
}
