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

import ModalTool from "./ModalTool.js";
import Security from "./Security.js";

const SIGN_IN_FORM_TEMPLATE_CONTENT =
    '<form class="col-sm-12" id="sign_in_form">' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="text" required="required" name="username" placeholder="User name" purpose="autoFocus"/>' +
    '       <span></span>' +
    '   </div>' +
    '   <div class="form-group">' +
    '       <input class="form-control"  type="password" required="required" name="password" placeholder="Password"/>' +
    '       <span></span>' +
    '   </div>' +
    '</form>';


const SIGN_IN_FORM_TEMPLATE_FOOTER =
    '<span class="pull-left" style="font-size:large;font-weight: bold;margin-top: 0.3em"></span>' +
    '<div>' +
    '   <input type="reset" class="btn btn-default" value="Reset" form="sign_in_form"/>' +
    '   <input type="submit" class="btn btn-primary" value="Submit" form="sign_in_form"/>' +
    '</div>';

const FORCE_REFRESH_BUTTON =
    '<input type="button" class="btn btn-success" value="Enter"/>';


export default class Login {

    constructor() {
    }


    onLogin() {
        ModalTool.setSize("small");
        ModalTool.setHeader("<span style='font-size: x-large;font-weight: bold'>User Login</span>", false);
        ModalTool.setBody(SIGN_IN_FORM_TEMPLATE_CONTENT);
        ModalTool.setFooter(SIGN_IN_FORM_TEMPLATE_FOOTER, false);
        ModalTool.show();
        ModalTool.setFocus($("input[purpose='autoFocus']"));
        let LoginObject = this;
        $("#sign_in_form").submit(function () {
            let formObject = $("#sign_in_form");
            let username = formObject.find("input[name='username']").val();
            let password = formObject.find("input[name='password']").val();
            new Security().login(username, password, function (info) {
                switch (info.code) {
                    case -1://for initialized (Security module)
                        Login._onInitialized(info);
                        break;
                    case 0://for ok (Security module)
                        Login._onRequestSuccess(info);
                        break;
                    case 1:
                        break;
                    default:
                        break;
                }
            });

            return false;
        });
    }

    static _onInitialized() {
        let footer = ModalTool.getFooterObject();
        let body = ModalTool.getBodyObject();
        body.find(".form-group").removeClass("has-error has-success has-warning");
        body.find("span").empty();
        footer.find("span").empty();
        footer.find("input").attr("disabled", "disabled");
        footer.find("span").removeClass("text-muted text-danger text-warning text-primary text-success");
        footer.find("span").addClass("text-info");
        footer.find("span").text('Submitting...');
    }

    static _onRequestSuccess(info) {
        let footer = ModalTool.getFooterObject();
        let body = ModalTool.getBodyObject();
        footer.find("span").empty();
        if (info.full.roleCode > 0) {
            footer.find("span").removeClass("text-muted text-danger text-warning text-primary text-info");
            footer.find("span").text('Login Successful!');
            footer.find("span").addClass("text-success");
            body.find(".form-group").addClass("has-success");
            footer.find("input").hide();
            footer.find("div").append(FORCE_REFRESH_BUTTON);
            footer.find("input[type='button']").click(function () {
                location.reload();
            });
            location.reload();
        } else {
            footer.find("input").removeAttr("disabled");
            footer.find("span").text('Login Failed!');
            footer.find("span").removeClass("text-muted text-info text-warning text-primary text-success");
            footer.find("span").addClass("text-danger");
            body.find(".form-group").addClass("has-error");
            body.find(".form-group:last").children("span").replaceWith("<span class='help-block'>Account or Password Incorrect.</span>")
        }
    }
}

