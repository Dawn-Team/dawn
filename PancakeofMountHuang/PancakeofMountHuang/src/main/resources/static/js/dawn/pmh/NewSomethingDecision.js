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

import ModalTool from "../../theWhiteSail/ModalTool.js";
import Editor from "../../theWhiteSail/Editor.js";
import PreProcessProject from "./PreProcessProject.js";


const HEADER_TEMPLATE =
    "<span style='font-size: x-large;font-weight: bold'>Choose Target</span>";

const DECISION_TEMPLATE =
    '<div class="col-sm-12">' +
    '   <input id="newArticle" type="button" class="btn btn-default form-control" value="New Article"/>' +
    '   <input id="newPreprocessProject" type="button" class="btn btn-default form-control" value="New Data Preprocess Project"/>' +
    '</div>';

export default class NewSomethingDecision {

    constructor() {

    }

    showDecisionUI() {
        ModalTool.setSize("small");
        ModalTool.setHeader(HEADER_TEMPLATE);
        ModalTool.setFooter(null, 'hidden');
        ModalTool.setBody(DECISION_TEMPLATE);
        ModalTool.show();
        let newArticleBtn = $("#newArticle");
        let newPreprocessProjectBtn = $("#newPreprocessProject");
        newArticleBtn.click(function () {
            ModalTool.show("hide");
            setTimeout(function () {
                new Editor().onNewArticle();
            }, 300);
            return false;
        });
        newPreprocessProjectBtn.click(function () {
            ModalTool.show("hide");
            setTimeout(function () {
                new PreProcessProject().onNewProject();
            },300);
            return false;
        })
    }
}