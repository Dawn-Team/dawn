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

import SignUp from "./SignUp.js";
import SignIn from "./Login.js"
import Security from "./Security.js";
import Util from "./Util.js";
import Editor from "./Editor.js";

$(document).ready(function () {
    let main = new Main();
    Main.setDraggable();
    main.setSignUp();
    main.setSignIn();
    main.setLogout();
    main.setNewArticle();
});

class Main {

    static setDraggable() {
        let info_col_ob = $("#info_col");
        let info_col_containment=info_col_ob.find(".list-group");
        let main_col_ob = $("#main_col");

        Util.setDraggable(info_col_ob.find("div div a"),info_col_containment,info_col_containment);
        Util.setDraggable(main_col_ob.find(".row"),main_col_ob,main_col_ob)

    }

    setSignUp() {
        $("#userSignUp").click(function () {
            new SignUp().onSignUp();
            return false;
        })
    }

    setSignIn() {
        $("#userLogIn").click(function () {
            new SignIn().onLogin();
            return false;
        })
    }

    setLogout() {
        $("#logout").click(function () {
            Security.logout();
            return false;
        });
    }

    setNewArticle(){
        $("#newArticle").click(function () {
            new Editor().onNewArticle();
            return false;
        })
    }
}


