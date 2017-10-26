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

import java.util.TreeMap;

/**
 * Project theWhiteSail
 * <p>
 * Author: arvinsc@foxmail.com
 * <p>
 * Date: 2017/9/28
 * <p>
 * Package: com.arvinsichuan.general
 */
public class WebTransmissionInfo extends TreeMap<Object, Object> {
    public enum infoName {status, code, exceptionInfo}

    public enum status {initialized, ok, failed,userExists}

    public WebTransmissionInfo() {
        put(infoName.status.name(), status.initialized);
        put(infoName.code.name(), status.initialized.ordinal());
    }

    public void failed() {
        put(infoName.status.name(), status.failed);
        put(infoName.code.name(), status.failed.ordinal());
    }

    public void ok() {
        put(infoName.status.name(), status.ok);
        put(infoName.code.name(), status.ok.ordinal());
    }

    public void exception(Object exceptionInfo) {
        failed();
        put(infoName.exceptionInfo.name(), exceptionInfo);
    }

}
