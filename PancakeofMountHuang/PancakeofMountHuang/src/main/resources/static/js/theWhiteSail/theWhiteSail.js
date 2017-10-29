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

import SignUp from "./SignUp.js";
import SignIn from "./Login.js"
import Security from "./Security.js";
import Util from "./Util.js";
import Editor from "./Editor.js";


export default class Main {

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


