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

package com.arvinsichuan.dawn.pmh.projectmanagement.service;

import com.arvinsichuan.dawn.pmh.datasource.entities.DatasourceEntity;
import com.arvinsichuan.dawn.pmh.datasource.repositories.DatasourceRepository;
import com.arvinsichuan.general.auth.SecurityInfo;
import com.arvinsichuan.general.scheduleplanner.AbstractAtomMission;
import com.arvinsichuan.general.scheduleplanner.MissionStatus;
import com.arvinsichuan.general.scheduleplanner.SchedulePlanner;
import com.arvinsichuan.general.scheduleplanner.exceptions.PriorityStrategyInvalidException;
import com.arvinsichuan.general.users.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 01-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.projectmanagement.service
 */
@Service("DPPProjectManager")
public class DPPProjectManager {
    private static DPPProjectManager ourInstance = new DPPProjectManager();

    @Resource(name = "priorityQueueSchedulePlanner")
    private SchedulePlanner schedulePlanner;

    @Resource(name = "datasourceRepo")
    private DatasourceRepository datasourceRepository;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public static DPPProjectManager getInstance() {
        return ourInstance;
    }

    private DPPProjectManager() {
    }

    private Map<UUID, DPPProjectService> services = new TreeMap<>();

    private List<AbstractAtomMission> submittedMissions = new ArrayList<>();

    public void registerMissions(List<AbstractAtomMission> missions) throws PriorityStrategyInvalidException {
        schedulePlanner.addABatchOfAtomMission(missions);
        submittedMissions.addAll(missions);
    }

    public void registerMissions(AbstractAtomMission... missions) throws PriorityStrategyInvalidException {
        registerMissions(Arrays.asList(missions));
    }

    public MissionStatus retrieveMissionStatus(UUID uuid) {
        MissionStatus status = null;
        List<AbstractAtomMission> batchMissions = new ArrayList<>();
        submittedMissions.forEach(mission -> {
            if (mission.getBatchUuid().equals(uuid)) {
                batchMissions.add(mission);
            }
        });
        if (batchMissions.size() == 1) {
            status = batchMissions.get(0).getStatus();
        } else {
            for (AbstractAtomMission mission : batchMissions) {
                if (mission.getPreRequisition() != null && mission.getStatus() != MissionStatus.FINISHED) {
                    status = mission.getStatus();
                    break;
                }
            }
        }
        return status;
    }

    public List<DatasourceEntity> getDataSourceList() {
        String username = SecurityInfo.getUsername();
        return datasourceRepository.findAllByRelatedUser(userRepository.findOne(username));
    }

    public DPPProjectService generateAProject() {
        UUID uuid = UUID.randomUUID();
        DPPProjectService service = new DPPProjectService(uuid);
        services.put(uuid, service);
        return service;
    }

}
