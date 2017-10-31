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

import com.arvinsichuan.dawn.pmh.datasource.exceptions.ParametersNotFoundException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.Map;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 31-Oct-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datasource
 * DPP=Data PreProcess
 * @author ArvinSiChuan
 */
public interface DPPDatasource {


    /**
     * Setting the parameters for Data Source.
     * @param parameters
     * @throws ParametersNotFoundException
     */
    public void setDatasourceParameters(Map parameters) throws ParametersNotFoundException, IOException, InvalidFormatException;


    /**
     * Access DatasourceCube Object.
     * @return
     */
    public DataSourceCube getDatasourceCube();


    /**
     * Access DatasourceCube Object with parameters.
     * @param parameters
     * @return
     * @throws ParametersNotFoundException
     */
    public DataSourceCube getDatasourceCubeWithParameters(Map parameters) throws ParametersNotFoundException,
            IOException, InvalidFormatException;
}
