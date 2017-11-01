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

package com.arvinsichuan.dawn.pmh.datacleaning.persistences.document;

import com.arvinsichuan.dawn.pmh.projectmanagement.service.DPPProjectService;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 01-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datacleaning.persistences
 * @author ArvinSiChuan
 */
@Document(collection = "data_pre_process_results")
public class DPPResultsDocument implements Serializable{

    @Id
    private String id;
    private UUID projectUuid;
    private List<String> squareLevelNames;
    private Map<String,List<String>> squareContents;

    public DPPResultsDocument(){}

    public DPPResultsDocument(String id, UUID projectUuid, List<String> squareLevelNames, Map<String, List<String>>
            squareContents) {
        this.id = id;
        this.projectUuid = projectUuid;
        this.squareLevelNames = squareLevelNames;
        this.squareContents = squareContents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UUID getProjectUuid() {
        return projectUuid;
    }

    public void setProjectUuid(UUID projectUuid) {
        this.projectUuid = projectUuid;
    }

    public List<String> getSquareLevelNames() {
        return squareLevelNames;
    }

    public void setSquareLevelNames(List<String> squareLevelNames) {
        this.squareLevelNames = squareLevelNames;
    }

    public Map<String, List<String>> getSquareContents() {
        return squareContents;
    }

    public void setSquareContents(Map<String, List<String>> squareContents) {
        this.squareContents = squareContents;
    }
}
