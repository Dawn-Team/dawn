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

import Security from "./Security.js";
import Util from "./Util.js";

const TAB_PANEL_TEMPLATE =
    '<div class="col-sm-12">' +
    '   <ul class="nav nav-tabs" role="tablist">' +
    '       <li role="presentation" class="active">' +
    '           <a href="#edit" aria-controls="home" role="tab" data-toggle="tab">Edit</a>' +
    '       </li>' +
    '       <li role="presentation">' +
    '              <a href="#preview" aria-controls="profile" role="tab" data-toggle="tab">Preview</a>' +
    '       </li>' +
    '       <!--<li role="presentation" >-->' +
    '           <!--<a href="#comments" aria-controls="profile" role="tab" data-toggle="tab">Comments</a>-->' +
    '       <!--</li>-->' +
    '   </ul>' +
    '   <!-- Tab panes -->' +
    '   <div class="tab-content">' +
    '       <div role="tabpanel" class="tab-pane fade in active" id="edit">' +
    '           <div class="col-sm-10" style="padding: 0;">' +
    '               <textarea class="form-control" rows="15"\n' +
    '                   style="resize: none;font-size: large;" maxlength="50000"' +
    '                   placeholder="maximum character limit: 5000"></textarea>' +
    '           </div>' +
    '           <div class="col-sm-2" style="padding-right: 0;">' +
    '               <form id="imgPickerForm" style="display: none;"></form>' +
    '               <label for="imgPicker" class="btn btn-default pull-right disabled" style="background: transparent;">' +
    '                   <span class="glyphicon glyphicon-picture" style="font-size:medium"></span>' +
    '               </label>' +
    '               <input id="imgPicker" name="file" type="file" style="display: none" form="imgPickerForm" required="required" disabled="disabled"/>' +
    '           </div>' +
    '       </div>' +
    '       <div role="tabpanel" class="tab-pane fade markdown-body" id="preview">' +
    '           <h1><span class="glyphicon glyphicon-hourglass"></span>Loading</h1>' +
    '       </div>' +
    '       <div role="tabpanel" class="tab-pane fade" id="comments">comments</div>' +
    '   </div>' +
    '</div>';


const ARTICLE_TEMPLATE =
    '<div class="row" data-toggle="editor"  style="display: none;">' +
    '   <form id="editorAnchor" hidden="hidden"></form>' +
    '   <div class="col-sm-12">' +
    '       <div class="panel panel-default">' +
    '           <div class="panel-heading" style="background: white">' +
    '               <h3 class="panel-title" style="font-size: x-large;font-weight: bold">' +
    '                   <div class="pull-right" role="toolbar" style="color: black;margin-top: 0.2em">' +
    '                   </div>' +
    '                   <input type="text" class="form-control" data-role="title" style="width: 50%" ' +
    '                       placeholder="New Article" form="editorAnchor" ' +
    '                       autofocus="autofocus" required="required"/>' +
    '               </h3>' +
    '           </div>' +
    '           <div class="panel-body">' +
    '           </div>' +
    '       </div>' +
    '   </div>' +
    '</div><!--row end-->';


//ICOs
const LOCK_ICO = '<a href="#" name="lock" style="margin-left: 5px;color: black"><span  class="glyphicon glyphicon-lock"></span></a>';
const DROP_ICO = '<a href="#" name="drop" style="margin-left: 5px;color: red"><span class="glyphicon glyphicon-minus-sign"></span></a>';
const SAVE_ICO = '<a href="#" name="save" style="margin-left: 5px;color: black;"><span class="glyphicon glyphicon-floppy-disk"></span></a>';
const SEND_ICO = '<a href="#" name="send" style="margin-left: 5px;color: black"><span class="glyphicon glyphicon-send"></span></a>';

let onEditingFlag = false;

export default class Editor {


    constructor() {
        this._previewTokenURL = "/editor/token";
        this._renderAPIURL = "https://api.github.com/markdown";
    }

    _requestRenderToken(recall_fun) {
        let csrfToken = Security.getCSRFToken();
        let editorOb = this;
        $.ajax({
            url: this._previewTokenURL,
            type: "POST",
            headers: csrfToken,
            async: true,
            dataType: "json",
            success: function (webInfo) {
                let renderToken = {};
                renderToken['Authorization'] = webInfo.Authorization;
                recall_fun(renderToken);
            },
            error: function (webInfo) {
                Util.wrapWebInfo(webInfo);
                editorOb._renderToken = null;
            }
        });
    }

    onEdit() {
    }

    onSave() {

    }

    _onPreview(text, recall_fun) {
        let tokenHeader;
        let renderAPIURL = this._renderAPIURL;
        let commitData = {
            "text": text,
            "mode": "markdown"
        };
        console.log(commitData);
        this._requestRenderToken(function (token) {
            tokenHeader = token;
            $.ajax({
                url: renderAPIURL,
                type: "POST",
                headers: tokenHeader,
                async: true,
                data: JSON.stringify(commitData),
                success: function (webInfo) {
                    recall_fun(webInfo);
                },
                error: function (webInfo) {
                    console.log(webInfo);
                }
            });
        });

    }

    onNewArticle() {
        let isFullyAuthOb = $("#isFullyAuth");
        let mainColOb = $("#main_col");
        let thisEditor = this;
        //TODO for debug and dev
        console.log(isFullyAuthOb);
        console.log(isFullyAuthOb.text());
        if (isFullyAuthOb === undefined || isFullyAuthOb.text() !== 'fullyAuthenticated') {
            return "auth insufficient";
        } else if (onEditingFlag === false) {
            mainColOb.find("div:first").before(ARTICLE_TEMPLATE);
            mainColOb.find("div[data-toggle='editor']").find('.panel-body').html(TAB_PANEL_TEMPLATE);
            mainColOb.find("div[role='toolbar']").html(SEND_ICO + SAVE_ICO + DROP_ICO);

            $("#editorAnchor").parent().find('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
                let currentTab = $(e.target);// newly activated tab
                let lastTab = $(e.relatedTarget);
                let currentTabPanel = $(currentTab.attr("href"));
                let lastTabPanel = $(lastTab.attr("href"));
                let text = lastTabPanel.find("textarea").val();
                if (currentTabPanel.is($("#preview"))) {
                    thisEditor._onPreview(text, function (data) {
                        console.log(data);
                        currentTabPanel.html(data);
                    });
                }
            });
            mainColOb.find("div[data-toggle='editor']").fadeIn("3000");
            onEditingFlag = true;
        } else {
            let editorOb = $("#editorAnchor");
            editorOb.click();
            editorOb.parent().effect('shake');
            editorOb.parent().find("input[data-role='title']").focus();
            return "on editing";
        }
    }

}