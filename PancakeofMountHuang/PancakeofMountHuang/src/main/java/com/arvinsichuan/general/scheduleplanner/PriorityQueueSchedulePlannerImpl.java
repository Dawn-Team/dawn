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

import com.arvinsichuan.general.scheduleplanner.exceptions.PriorityStrategyInvalidException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

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
@Service("priorityQueueSchedulePlanner")
public class PriorityQueueSchedulePlannerImpl implements SchedulePlanner {

    private static PriorityQueueSchedulePlannerImpl ourInstance = new PriorityQueueSchedulePlannerImpl();

    public static PriorityQueueSchedulePlannerImpl getInstance() {
        return ourInstance;
    }

    private Queue<AbstractAtomMission> priorityQueue = new PriorityQueue<>();

    private Queue<AbstractAtomMission> cachedPreRequisitionQueue = new PriorityQueue<>();

    private List<Thread> localThreadPool = new ArrayList<>();

    private int threadThreshold = 5;

    private long heartPause = 100;
//
//    private ExecutorService singleThreadExcutor = new ThreadPoolExecutor(
//            20, 50, 30,
//            TimeUnit.MINUTES,
//            new ArrayBlockingQueue<Runnable>(10));


    private PriorityQueueSchedulePlannerImpl() {
        new Thread(this).start();
    }

    @Override
    public SchedulePlanner addAnAtomMission(AbstractAtomMission atomMission) throws PriorityStrategyInvalidException {
        synchronized (this) {
            if (!priorityQueue.contains(atomMission)) {
                testPriority(atomMission);
                priorityQueue.add(atomMission);
            }
        }
        return this;
    }

    @Override
    public SchedulePlanner addABatchOfAtomMission(List<AbstractAtomMission> atomMissions) throws
            PriorityStrategyInvalidException {
        synchronized (this) {
            if (!priorityQueue.containsAll(atomMissions)) {
                for (AbstractAtomMission atomMission : atomMissions) {
                    testPriority(atomMission);
                }
                priorityQueue.addAll(atomMissions);
            }
        }
        return this;
    }


    private void testPriority(AbstractAtomMission atomMission) throws PriorityStrategyInvalidException {
        if (atomMission.getPreRequisition() != null) {
            AbstractAtomMission preMission = atomMission.getPreRequisition();
            if (preMission.getPriority() < atomMission.getPriority()) {
                throw new PriorityStrategyInvalidException("greater than or equal to" + atomMission.getPriority());
            }
        }
    }


    @Override
    public void run() {
        while (true) {
            if (localThreadPool.size() >= threadThreshold) {
                heartPause = 3000;
            } else if (priorityQueue.size() == 0 && cachedPreRequisitionQueue.size() == 0) {
                heartPause = 3000;
            } else {
                System.out.println("###################  Resources Sufficient  #########################");
                AbstractAtomMission mission = null;
                if (cachedPreRequisitionQueue.size() > 0) {
                    mission = cachedPreRequisitionQueue.peek();
                    if (mission.getPreRequisition() == null ||
                            mission.getPreRequisition().getStatus() == MissionStatus.FINISHED) {
                        System.out.println("###################  Polling out cached  #########################");
                        mission = cachedPreRequisitionQueue.poll();
                    } else {
                        mission = null;
                    }
                }
                if (mission == null && priorityQueue.size() > 0) {
                    System.out.println("###################  Polling out Priority  #########################");
                    mission = priorityQueue.poll();
                }

                if (mission != null) {
                    heartPause = 100;
                    if (mission.getPreRequisition() == null ||
                            mission.getPreRequisition().getStatus() == MissionStatus.FINISHED) {
                        Thread thread = new Thread(mission);
                        localThreadPool.add(thread);
                    } else {
                        mission.setStatus(MissionStatus.WAITING);
                        cachedPreRequisitionQueue.add(mission);
                    }
                }
            }

            if (localThreadPool.size() > 0) {
                localThreadPool.get(0).start();
                localThreadPool.remove(0);
            }


            try {
                System.out.println("*************************************************************\nListening......");
                priorityQueue.forEach(mission -> {
                    System.out.println(mission.getMissionUuid().toString() + mission.getStatus());
                });
                System.out.println("--------------------------------------------------------------");
                cachedPreRequisitionQueue.forEach(mission -> {
                    System.out.println(mission.getMissionUuid().toString() + mission.getStatus());
                });
                System.out.println("***********************  DEBUGGING  **************************");
                Thread.sleep(heartPause);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
