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
import Login from './Login.js'

const SIGN_UP_FORM_TEMPLATE_CONTENT =
    '<form class="col-sm-12" id="sign_up_form">' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="text" required="required" name="username" placeholder="User name" data-purpose="autoFocus"/>' +
    '       <span></span>' +
    '   </div>' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="password" required="required" name="password" placeholder="Password"/>' +
    '   </div>' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="password" required="required" name="password_repeat" placeholder="Repeat Password"/>' +
    '   </div>' +
    '</form>';


const SIGN_UP_FORM_TEMPLATE_FOOTER =
    '<span class="pull-left" style="font-size:large;font-weight: bold;margin-top: 0.3em;text-align: left"></span>' +
    '<input type="reset" class="btn btn-default" value="Reset" form="sign_up_form"/>' +
    '<input type="submit" class="btn btn-primary" value="Submit" form="sign_up_form"/>';

const LOGIN_BTN =
    '<input type="button" class="btn btn-default"  style="margin-top: 0.3em" value="Log In"/>';


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
                recall_fun(Util.wrapWebInfo(webInfo));
            },
            error: function (webInfo) {
                Security.ajaxError(webInfo);
                recall_fun(Util.wrapWebInfo(webInfo));
            }
        });
    }

    _onSubmitSuccess(info) {
        let footer = ModalTool.getFooterObject();
        let body = ModalTool.getBodyObject();
        if (info.code === 0) {
            footer.find("span").removeClass("text-muted text-danger text-warning text-primary text-info");
            footer.find("span").html('Sign up <br />Successful!');
            footer.find("span").addClass("text-success");
            body.find(".form-group").addClass("has-success");
            footer.find("input").hide();
            footer.append(LOGIN_BTN);
            footer.find("input[type='button']").click(function () {
                ModalTool.show('hide');
                new Login().onLogin();
                body.find("input[name='username']").val(info.full.user.username);
                body.find("input[name='password']").focus();
            });
        } else {
            footer.find("input").removeAttr("disabled");
            footer.find("span").text('Sign up Failed!');
            footer.find("span").removeClass("text-muted text-info text-warning text-primary text-success");
            footer.find("span").addClass("text-danger");
            body.find(".form-group:first").addClass("has-error");
            body.find(".form-group:first").children("span").replaceWith("<span class='help-block'>User Already Exists.</span>")
        }
    }

    onSignUp() {
        ModalTool.setSize("small");
        ModalTool.setHeader("<span style='font-size: x-large;font-weight: bold;'>User Sign up</span>", false);
        ModalTool.setBody(SIGN_UP_FORM_TEMPLATE_CONTENT);
        ModalTool.setFooter(SIGN_UP_FORM_TEMPLATE_FOOTER, false);
        ModalTool.show();
        ModalTool.setFocus($("input[data-purpose='autoFocus']"));
        let signUpObject = this;
        let footer = ModalTool.getFooterObject();
        $("#sign_up_form").submit(function () {
            let formObject = $("#sign_up_form");
            let username = formObject.find("input[name='username']").val();
            let password = formObject.find("input[name='password']").val();
            let passwordRepeat = formObject.find("input[name='password_repeat']").val();
            if (password === passwordRepeat) {
                footer.find("span").empty();
                footer.find("input").attr("disabled", "disabled");
                footer.find("span").removeClass("text-muted text-danger text-warning text-primary text-success");
                footer.find("span").addClass("text-info");
                footer.find("span").text('Submitting...');
                signUpObject.onSubmit(username, password, function (info) {
                    signUpObject._onSubmitSuccess(info)
                })
            } else {
                formObject.find(".form-group:gt(0)").addClass("has-error");
                formObject.find(".form-group:gt(0)").append("<span class='help-block'>Password should be the same.</span>")
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