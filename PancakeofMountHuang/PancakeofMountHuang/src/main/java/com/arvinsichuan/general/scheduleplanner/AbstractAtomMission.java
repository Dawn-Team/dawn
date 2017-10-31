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

package com.arvinsichuan.general.scheduleplanner;

import java.io.Serializable;
import java.util.UUID;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 31-Oct-17
 * <p>
 * Package: com.arvinsichuan.general.scheduleplanner
 * @author ArvinSiChuan
 */
public abstract class AbstractAtomMission implements Runnable,Serializable {
    private static final long serialVersionUID = -7922589714247736889L;

    private UUID missionUuid=UUID.randomUUID();
    private UUID preRequisition=null;
    private UUID postRequisition=null;

}
