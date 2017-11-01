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
import UploadFile from "./UploadFile.js";

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

const TEMP_TEMPLATE =
    '<h4>Step 1: Choose A Data Pre-Process Datasource.</h4>' +
    '<h5>Available Datasources:</h5>' +
    '<div class="table-responsive">' +
    '   <table name="DPPDSTable" class="table table-hover">' +
    '       <thead style="font-weight: bold;">' +
    '           <td>DS UUID</td>' +
    '           <td>DS Available Alias</td>' +
    '           <td>DS Type</td>' +
    '       </thead>' +
    '   <tbody>' +
    '       <tr>' +
    '           <td colspan="999" class="info">No avaliable DS now.</td>' +
    '       </tr>' +
    '   </tbody>' +
    '   <tfoot>' +
    '       <td colspan="999">Selected DS: <span style="color: red;">Nothing</span></td>' +
    '   </tfoot>' +
    '   </table>' +
    '</div>' +
    '<hr />' +
    '<h5>Upload an excel file as datasource: </h5>' +
    '<div data-id="DSUploadComponent">' +
    '</div>' +
    '<h4>Step 2: Choose Datasource Details. </h4>' +
    '<h5>2.1 Choose workbook</h5>' +
    '<div>' +
    '   <select class="form-control">' +
    '       <option>No available workbook</option>' +
    '   </select>' +
    '</div>' +
    '<h5>2.2 Choose Columns</h5>' +
    '<div>' +
    '  <select class="form-control">' +
    '    <option>No abailable columns</option>' +
    '  </select>' +
    '</div>' +
    '<hr />' +
    '<h4>Step 3. Choose An Algorithm.</h4>' +
    '<div>' +
    '   <select class="form-control">' +
    '       <option>No available algoritms</option>' +
    '   </select>' +
    '</div>' +
    '<br />' +
    '<hr />' +
    '<div hidden="hidden">' +
    '   <p>Progress:</p>' +
    '</div>' +
    '<div>' +
    '   <input type="button" class="btn btn-primary" value="Submit Mission" />' +
    '</div>';


//ICOs
const LOCK_ICO = '<a href="#" name="lock" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span  class="glyphicon glyphicon-lock"></span></a>';
const DROP_ICO = '<a href="#" name="drop" class="btn" style="padding: 5px;color: red;font-size: x-large;"><span class="glyphicon glyphicon-minus-sign"></span></a>';
const SAVE_ICO = '<a href="#" name="save" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span class="glyphicon glyphicon-floppy-disk"></span></a>';
const SEND_ICO = '<a href="#" name="send" class="btn" style="padding: 5px;color: black;font-size: x-large;"><span class="glyphicon glyphicon-send"></span></a>';

let onCreateFlag = 0;

export default class PreProcessProject {


    constructor() {
        this._multitaskThreshold = 1;
        this._onCreatingOb = $("div[data-toggle='newPreprocessProject']");
    }


    onNewProject() {
        let isFullyAuthOb = $("#isFullyAuth");
        let mainColOb = $("#main_col");
        let thisObject = this;
        if (isFullyAuthOb === undefined || isFullyAuthOb.text() !== 'fullyAuthenticated') {
            return "auth insufficient";
        } else if (onCreateFlag < this._multitaskThreshold) {
            mainColOb.find("div:first").before(ARTICLE_TEMPLATE);
            mainColOb.find("div[role='toolbar']").html(SEND_ICO + SAVE_ICO + DROP_ICO);
            mainColOb.find("div[data-toggle='newPreprocessProject']").fadeIn("3000");
            this._onCreatingOb = $("div[data-toggle='newPreprocessProject']");
            this._onCreatingOb.find(".panel-body").html(TEMP_TEMPLATE);
            let container = this._onCreatingOb.find("div[data-id='DSUploadComponent']");
            console.log("*******************************");
            console.log(container);
            new UploadFile().onNewUpload(container, function (data) {
                thisObject._onFileUploadSuccess(data);
            });

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

    _onFileUploadSuccess(data) {

    }


}