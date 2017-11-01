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
 *
 * @author ArvinSiChuan
 */
public abstract class AbstractAtomMission implements Runnable, Serializable, Comparable {

    private UUID missionUuid = UUID.randomUUID();
    private UUID batchUuid = null;
    private AbstractAtomMission preRequisition = null;
    private AbstractAtomMission postRequisition = null;
    private int priority = -1;
    private MissionStatus status;

    public UUID getMissionUuid() {
        return missionUuid;
    }

    public void setMissionUuid(UUID missionUuid) {
        this.missionUuid = missionUuid;
    }

    public AbstractAtomMission getPreRequisition() {
        return preRequisition;
    }

    public void setPreRequisition(AbstractAtomMission preRequisition) {
        this.preRequisition = preRequisition;
    }

    public AbstractAtomMission getPostRequisition() {
        return postRequisition;
    }

    public void setPostRequisition(AbstractAtomMission postRequisition) {
        this.postRequisition = postRequisition;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public MissionStatus getStatus() {
        return status;
    }

    public void setStatus(MissionStatus status) {
        this.status = status;
    }

    public UUID getBatchUuid() {
        return batchUuid;
    }

    public void setBatchUuid(UUID batchUuid) {
        this.batchUuid = batchUuid;
    }

    @Override
    public int compareTo(Object o) {
        AbstractAtomMission mission = (AbstractAtomMission) o;
        return mission.getPriority() - this.getPriority();
    }
}
