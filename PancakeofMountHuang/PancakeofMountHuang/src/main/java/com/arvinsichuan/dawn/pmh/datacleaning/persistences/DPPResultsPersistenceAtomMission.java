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

package com.arvinsichuan.dawn.pmh.datacleaning.persistences;

import com.arvinsichuan.dawn.pmh.datacleaning.persistences.document.DPPResultsDocument;
import com.arvinsichuan.dawn.pmh.datacleaning.persistences.repository.DPPResultsDocumentsRepo;
import com.arvinsichuan.general.exceptions.EmptyDataException;
import com.arvinsichuan.general.scheduleplanner.AbstractAtomMission;
import com.arvinsichuan.general.scheduleplanner.MissionStatus;

import javax.annotation.Resource;

/**
 * Project PancakeofMountHuang
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 02-Nov-17
 * <p>
 * Package: com.arvinsichuan.dawn.pmh.datacleaning.persistences
 */
public class DPPResultsPersistenceAtomMission extends AbstractAtomMission {

    private static final long serialVersionUID = -6996386206762480833L;

    @Resource(name = "DPPResultsDocsRepo")
    private DPPResultsDocumentsRepo dppResultsDocumentsRepo;

    private DPPResultsDocument document;

    public DPPResultsPersistenceAtomMission(DPPResultsDocument document) {
        this.document = document;
    }

    public DPPResultsDocumentsRepo getDppResultsDocumentsRepo() {
        return dppResultsDocumentsRepo;
    }

    public void setDppResultsDocumentsRepo(DPPResultsDocumentsRepo dppResultsDocumentsRepo) throws EmptyDataException {
        testDocument();
        this.dppResultsDocumentsRepo = dppResultsDocumentsRepo;
    }

    public DPPResultsDocument getDocument() {
        return document;
    }

    public void setDocument(DPPResultsDocument document) throws EmptyDataException {
        testDocument();
        this.document = document;
    }

    private void testDocument() throws EmptyDataException {
        if (document == null) {
            throw new EmptyDataException("document can't be null");
        }
    }

    @Override
    public void run() {
        setStatus(MissionStatus.RUNNING);
        dppResultsDocumentsRepo.save(document);
        setStatus(MissionStatus.FINISHED);
    }
}
