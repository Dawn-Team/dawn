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
const ARTICLE_TEMPLATE =
    '<div class="row" data-toggle="newPreprocessProject"  style="display: none;">' +
    '   <div class="col-sm-12">' +
    '       <div class="panel panel-default">' +
    '           <div class="panel-heading" style="background: white">' +
    '               <h3 class="panel-title" style="font-size: x-large;font-weight: bold">' +
    '                   <div class="pull-right" role="toolbar" style="color: black;">' +
    '                   </div>' +
    '                   <input type="text" class="form-control" data-role="title" style="width: 50%" ' +
    '                       placeholder="New Data Pre-process Project"  autofocus="autofocus" required="required"/>' +
    '               </h3>' +
    '           </div>' +
    '           <div class="panel-body">' +
    '           </div>' +
    '       </div>' +
    '   </div>' +
    '</div><!--row end-->';


//ICOs
const LOCK_ICO = '<a href="#" name="lock" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span  class="glyphicon glyphicon-lock"></span></a>';
const DROP_ICO = '<a href="#" name="drop" class="btn" style="padding: 5px;color: red;font-size: x-large;"><span class="glyphicon glyphicon-minus-sign"></span></a>';
const SAVE_ICO = '<a href="#" name="save" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span class="glyphicon glyphicon-floppy-disk"></span></a>';
const SEND_ICO = '<a href="#" name="send" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span class="glyphicon glyphicon-send"></span></a>';

let onCreateFlag = 0;

export default class PreProcessProject {


    constructor() {
        this._multitaskThreshold = 1;
        this._onCreatingOb=$("div[data-toggle='newPreprocessProject']");
    }


    onNewProject() {
        let isFullyAuthOb = $("#isFullyAuth");
        let mainColOb = $("#main_col");
        let thisEditor = this;
        if (isFullyAuthOb === undefined || isFullyAuthOb.text() !== 'fullyAuthenticated') {
            return "auth insufficient";
        } else if (onCreateFlag < this._multitaskThreshold) {
            mainColOb.find("div:first").before(ARTICLE_TEMPLATE);
            mainColOb.find("div[role='toolbar']").html(SEND_ICO + SAVE_ICO + DROP_ICO);
            mainColOb.find("div[data-toggle='newPreprocessProject']").fadeIn("3000");
            this._onCreatingOb=$("div[data-toggle='newPreprocessProject']");
            this._onCreatingOb.find(".panel-body").html("TODO HERE");

            this._onCreatingOb.find("a[name='send']").addClass("disabled");
            this._onCreatingOb.find("a[name='save']").addClass("disabled");

            onCreateFlag++;
        } else {
            this._onCreatingOb.click();
            this._onCreatingOb.effect('shake');
            this._onCreatingOb.find("input[data-role='title']").focus();
            return "on creating";
        }
    }

}