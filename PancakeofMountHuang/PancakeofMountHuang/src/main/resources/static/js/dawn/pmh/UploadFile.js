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
import Security from '../../theWhiteSail/Security.js'

const STEP1_TEMPLATE ='<form id=\"uploadform\" enctype=\"multipart/form-data\">\n' +
    '    <input type=\"file\" id=\"myBlogImage\" name=\"myfiles\"class="btn btn-default"  />\n '+
    '    <br><input type=\"submit\" id=\"upload\" value=\"上传\" class="btn btn-primary" />\n' +
    '</form>';

export default class UploadFile{

    constructor(){}

    onNewUpload(){
        let csrfToken = Security.getCSRFToken();
        $("div[data-toggle='newPreprocessProject']").find(".panel-body").html(STEP1_TEMPLATE);
        $("#uploadform").submit(function () {
            let  formData = new FormData($(this)[0]);
            $.ajax({
                //处理文件上传操作的服务器端地址(可以传参数,已亲测可用)http://localhost:8080
                url: '/fileUpload/upload',
                type: 'PUT',
                headers: csrfToken,
                data: formData,
                async: true,
                cache: true,
                contentType: false,
                processData: false,
                success: function () {            //服务器响应成功时的处理函数
                    alert("上传成功!");
                },
                error: function () { //服务器响应失败时的处理函数
                    alert("上传失败!");
                }
            });
            return false;
        }
    );
    }

    onUpload(formData){

    }

}