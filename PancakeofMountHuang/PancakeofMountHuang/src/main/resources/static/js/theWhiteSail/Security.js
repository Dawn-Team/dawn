/*
 * Copyright (c) 2017.  ArvinSiChuan.com. All Right Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import Util from "./Util.js";

const logoutURL="/auth/logout";

export default class Security {

    // status code list
    // 1 - exception
    // 0 - ok
    // -1 - initialized

    constructor() {
        this._loginURL = "/auth/login";

    }

    static getCSRFToken() {
        let header = $("meta[name='_csrf_header']").attr("content");
        let token = $("meta[name='_csrf']").attr("content");
        let headerToken = {};
        headerToken[header] = token;
        return headerToken;
    }

    _submitLogin(username, password, recall_fun) {
        let csrf_token = Security.getCSRFToken();
        $.ajax({
            url: this._loginURL,
            type: 'POST',
            async: true,
            headers: csrf_token,
            contentType: "application/x-www-form-urlencoded;charset:utf-8;",
            data: {
                username: username,
                password: password
            },
            success: function (data) {
                Util.wrapWebInfo(data, recall_fun);
            },
            error: function (data) {
                Security.ajaxError(data);
                Util.wrapWebInfo(data, recall_fun);
            }
        })
    }

    login(username, password, recall_fun) {
        let loginInfo = {};
        loginInfo["status"] = "initialized";
        loginInfo["code"] = -1;
        if (username === null || username === undefined) {
            loginInfo.status = "invalid_username_input";
            loginInfo.code = 1;
            recall_fun(loginInfo);
        } else if (password === null || password === undefined) {
            loginInfo.status = "invalid_password_input";
            loginInfo.code = 1;
            recall_fun(loginInfo);
        } else {
            this._submitLogin(username, password, recall_fun);
            recall_fun(loginInfo);
        }
    }

    static logout() {
        let csrfHeader = this.getCSRFToken();
        $.ajax({
            url: logoutURL,
            type: 'POST',
            async: true,
            headers: csrfHeader,
            success: function () {
                location.reload();
            },
            error: function () {
                location.reload();
            }
        });
        return {"status": "ok", "code": 0};
    }


    static ajaxError(data) {
        switch (data.status) {
            case 200:
                alert("Authentication expired.");
                //  location.reload();
                break;
            case 403:
                alert('Access Denied.');
                Security.logout();
                break;
            case 400:
                alert("code:400");
                break;
            case 0:
                alert('Too quick refresh or click');
                break;
            default:
                alert('Error:' + data.status);
                break;
        }
    }

}

