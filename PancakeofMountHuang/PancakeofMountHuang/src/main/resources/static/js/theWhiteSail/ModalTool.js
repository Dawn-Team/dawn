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

export default class ModalTool {
    

    static setSize(size) {
        switch (size) {
            case "small":
                this._getModalObject().removeClass("bs-example-modal-lg");
                this._getModalObject().children("div").removeClass("modal-lg");
                this._getModalObject().children("div").addClass("modal-sm");
                this._getModalObject().addClass("bs-example-modal-sm");
                break;
            case "large":
                this._getModalObject().removeClass("bs-example-modal-sm");
                this._getModalObject().children("div").removeClass("modal-sm");
                this._getModalObject().children("div").addClass("modal-lg");
                this._getModalObject().addClass("bs-example-modal-lg");
                break;
            default:
                console.log("WARNING: no such size");
                break;
        }
        // set content to default
        ModalTool.setHeader("Loading");
        ModalTool.setBody("Loading");
        ModalTool.setFooter(null, "hidden");

    }
    static setFocus(object){
        this._getModalObject().on('shown.bs.modal', function () {
            object.focus();
        });
    }

    static setHeader(header, hidden) {
        if (hidden === "hidden") {
            ModalTool.getHeaderObject().attr("hidden", hidden);
        } else {
            this._getModalObject().find(".modal-header").removeAttr("hidden");
        }
        this._getModalObject().find(".modal-header").html(header);
    }

    static getHeaderObject() {
        if (this._header === null || this._header === undefined) {
            this._header = this._getModalObject().find(".modal-header");
        }
        return this._header;
    }

    static setBody(content) {
        ModalTool.getBodyObject().find("div[purpose='placeholder']").html(content)
    }

    static getBodyObject() {
        if (this._body === null || this._body === undefined) {
            this._body = this._getModalObject().find(".modal-body");
        }
        return this._body;
    }

    static setFooter(content, hidden) {
        if (hidden === "hidden") {
            ModalTool.getFooterObject().attr("hidden", hidden)
        } else {
            ModalTool.getFooterObject().removeAttr("hidden")
        }
        ModalTool.getFooterObject().html(content)
    }

    static getFooterObject() {
        if (this._footer === null || this._footer === undefined) {
            this._footer = this._getModalObject().find(".modal-footer");
        }
        return this._footer;
    }

    static _getModalObject() {
        if (this._modalObject === null || this._modalObject === undefined) {
            this._modalObject = $(".modal")
        }
        return this._modalObject
    }

    static show(show) {
        if (show === null || show === undefined || show) {
            this._getModalObject().modal('show')
        } else {
            this._getModalObject().modal('hide')
        }
    }
}