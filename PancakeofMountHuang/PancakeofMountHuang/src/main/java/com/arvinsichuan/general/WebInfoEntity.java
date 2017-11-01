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

package com.arvinsichuan.general;

import com.arvinsichuan.general.exceptions.DuplicatedDataException;

import java.util.TreeMap;

/**
 * Project TrianTicketsService -  Serverside
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/10/20
 * <p>
 * Package: com.arvinsichuan.androidtraining.serversside.general
 *
 * @author Arvin
 */
public class WebInfoEntity extends TreeMap<String, Object> {

    private static final long serialVersionUID = -8268463626611179619L;
    private static final String EMPTY_DATA_CODE = "01";
    private static final String DUPLICATED_DATA_CODE = "02";

    public enum Status {
        /**
         * OK - Status OK
         * Status code starts with 0 (0[0-9]*)
         */
        OK,
        /*
         *  EXCEPTION - Has Exception or have Exceptions
         *  Error code starts with prefix 1 (1[0-9]*)
        */
        EXCEPTION,
        /**
         * non of data found Error code 101
         */
        EMPTY_DATA,
        /*
        *   Duplicated data found which can not be.
        *   Error code 102
        */
        DUPLICATE_DATA
    }


    public WebInfoEntity isOK() {
        put("status", Status.OK);
        put("code", Status.OK.ordinal());
        return this;
    }

    public WebInfoEntity haveException(Exception e) {
        put("status", Status.EXCEPTION);
        put("code", Status.EXCEPTION.ordinal());
        put("message", e.getMessage());
        return this;
    }

    public WebInfoEntity haveException(Exception e, String message) {
        put("status", Status.EXCEPTION);
        put("code", Status.EXCEPTION.ordinal());
        put("message", e.getMessage() + "; Additional Message:" + message);
        return this;
    }

    public WebInfoEntity addInfoAndData(String key, Object object) {
        put(key, object);
        return this;
    }

    public WebInfoEntity emptyData() {
        return haveException(new DuplicatedDataException(Status.EMPTY_DATA.toString()))
                .setCode(Status.EXCEPTION.ordinal() + EMPTY_DATA_CODE);
    }

    public WebInfoEntity emptyData(String message) {
        return haveException(new DuplicatedDataException(Status.EMPTY_DATA+": "+message))
                .setCode(Status.EXCEPTION.ordinal() + EMPTY_DATA_CODE);
    }

    public WebInfoEntity duplicatedData() {
        return haveException(new DuplicatedDataException(Status.DUPLICATE_DATA.toString()))
                .setCode(Status.EXCEPTION.ordinal() + DUPLICATED_DATA_CODE);
    }

    public WebInfoEntity duplicatedData(String message) {
        return haveException(new DuplicatedDataException(Status.DUPLICATE_DATA + ": " + message))
                .setCode(Status.EXCEPTION.ordinal() + DUPLICATED_DATA_CODE);
    }

    private WebInfoEntity setCode(String code) {
        put("code", code);
        return this;
    }

}
