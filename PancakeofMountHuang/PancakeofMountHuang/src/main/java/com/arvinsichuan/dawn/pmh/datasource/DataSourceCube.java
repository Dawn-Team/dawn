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

package com.arvinsichuan.dawn.pmh.datasource;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 31-Oct-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datasource
 *
 * @author ArvinSiChuan
 */
public class DataSourceCube implements Serializable {
    private static final long serialVersionUID = 2341066689913762597L;

    private List<String> cubeLevelNames;
    private Map<String, List> squareLevelNames;
    private List<Map<String, List>> cube;

    public DataSourceCube() {

    }

    public DataSourceCube(List<String> cubeLevelNames, Map<String, List> squareLevelNames, List<Map<String, List>>
            cube) {
        this.cubeLevelNames = cubeLevelNames;
        this.squareLevelNames = squareLevelNames;
        this.cube = cube;
    }

    public List<String> getCubeLevelNames() {
        return cubeLevelNames;
    }

    public void setCubeLevelNames(List<String> cubeLevelNames) {
        this.cubeLevelNames = cubeLevelNames;
    }

    public Map<String, List> getSquareLevelNames() {
        return squareLevelNames;
    }

    public void setSquareLevelNames(Map<String, List> squareLevelNames) {
        this.squareLevelNames = squareLevelNames;
    }

    public List<Map<String, List>> getCube() {
        return cube;
    }

    public void setCube(List<Map<String, List>> cube) {
        this.cube = cube;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < cubeLevelNames.size(); i++) {
            String sheetName = cubeLevelNames.get(i);
            str += sheetName + "\n";
            str += "========================================\n";
            Map sheet = cube.get(i);
            List<String> names = squareLevelNames.get(sheetName);
            for (String name :
                    names) {
                str += "--------------------------------------\n";
                str += name + "\n";
                str += "--------------------------------------\n";
                List<String> contents = (List<String>) sheet.get(name);
                for (String content :
                        contents) {
                    str += content + "\n";
                }
                str += "**************************************\n";
            }
        }
        return str;
    }
}
