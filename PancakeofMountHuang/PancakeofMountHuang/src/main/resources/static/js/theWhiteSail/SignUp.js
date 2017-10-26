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

import ModalTool from './ModalTool.js'
import Security from './Security.js'
import Util from './Util.js'

const SIGN_UP_FORM_TEMPLATE_CONTENT =
    '<form class="col-sm-12" id="sign_up_form">' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="text" required="required" name="username" placeholder="User name" purpose="autoFocus"/>' +
    '   </div>' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="password" required="required" name="password" placeholder="Password"/>' +
    '   </div>' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="password" required="required" name="password_repeat" placeholder="Repeat Password"/>' +
    '   </div>' +
    '</form>';


const SIGN_UP_FORM_TEMPLATE_FOOTER =
    '<input type="reset" class="btn btn-default" value="Reset" form="sign_up_form"/>' +
    '<input type="submit" class="btn btn-primary" value="Submit" form="sign_up_form"/>';


export default class SignUp {

    constructor() {
        this._signUpURL = "/users/signUp";
    }

    // status code list
    // 1 - ok
    // 0 - inited
    // -1 - invalid username input
    // -2 invalid password input


    _submit(username, password, recall_fun) {
        let csrfToken = Security.getCSRFToken();
        $.ajax({
            url: this._signUpURL,
            type: "POST",
            headers: csrfToken,
            async: true,
            dataType: "json",
            contentType: "application/x-www-form-urlencoded;charset:utf-8;",
            data: {
                username: username,
                password: password
            },
            success: function (webInfo) {
                Util.wrapWebInfo(webInfo, recall_fun)
            },
            error: function (webInfo) {
                Security.ajaxError(webInfo);
                Util.wrapWebInfo(webInfo, recall_fun)
            }
        });
    }

    onSignUp() {
        ModalTool.setSize("small");
        ModalTool.setHeader("<span style='font-size: x-large;font-weight: bold;'>User Sign up</span>", false);
        ModalTool.setBody(SIGN_UP_FORM_TEMPLATE_CONTENT);
        ModalTool.setFooter(SIGN_UP_FORM_TEMPLATE_FOOTER, false);
        ModalTool.show();
        ModalTool.setFocus($("input[purpose='autoFocus']"));
        let signUpObject = this;
        $("#sign_up_form").submit(function () {
            let formObject = $("#sign_up_form");
            let username = formObject.find("input[name='username']").val();
            let password = formObject.find("input[name='password']").val();
            let passwordRepeat = formObject.find("input[name='password_repeat']").val();
            if (password === passwordRepeat) {
                signUpObject.onSubmit(username, password, function (info) {
                    console.log(info);
                })
            } else {
                formObject.find(".form-group:gt(0)").addClass("has-error");
                formObject.find(".form-group:gt(0)").append("<span class='help-block'>Indifferent password</span>")
            }


            return false
        })
    }

    onSubmit(username, password, recall_fun) {
        let submitInfo = {};
        submitInfo["status"] = "initialized";
        submitInfo["code"] = 0;
        if (username === null) {
            submitInfo.status = "invalid_username_input";
            submitInfo.code = "-1";
            recall_fun(submitInfo)
        } else if (password === null) {
            submitInfo.status = "invalid_password_input";
            submitInfo.code = "-2";
            recall_fun(submitInfo)
        } else {
            this._submit(username, password, recall_fun)
        }
    }
}