import React from "react";
import Modal from "react-bootstrap"


export default class Login_Registrazione extends React.Component{
   
    render(){
        return (
            <div class="modal fade" id="login-modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="display: none;">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header" align="center">
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span class="fa fa-times" aria-hidden="true"></span>
                            </button>
                        </div>
                        <div id="div-forms">
                            <form id="login-form">
                                <h3 class="text-center">Accedi</h3>
                                <div class="modal-body">
                                    <label for="username">Email istituzionale</label> 
                                    <input id="login_username" class="form-control" type="text" placeholder="" required></input>
                                    <label for="username">Password</label> 
                                    <input id="login_password" class="form-control" type="password" placeholder="" required></input>
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox"> Remember me</input>
                                        </label>
                                     </div>
                                <div class="modal-footer text-center">
                                <div>
                                    <button type="submit" class="btn btn-general btn-white">Login</button>
                                </div>
                                <div>
                                    <button id="login_register_btn" type="button" class="btn btn-link">Register</button>
                                </div>
                            </form>
                            <form id="register-form" style="display:none;">
                                <h3 class="text-center">Register</h3>
                                <div class="modal-body"> 
                                    <label for="username">Nome</label> 
                                    <input id="register_username" class="form-control" type="text" placeholder="" required></input>
                                    <label for="cognome">Cognome</label> 
							        <input id="register_surname" class="form-control" type="text" placeholder="" required></input>
							        <label for="cognome">Data di nascita</label> 
							        <input id="register_surname" class="form-control" type="date" placeholder="" required></input>
                                    <label for="register_email">Email</label> 
                                    <input id="register_email" class="form-control" type="text" placeholder="" required></input>
                                    <label for="register_password">Password</label> 
                                    <input id="register_password" class="form-control" type="password" placeholder="" required maxlength="8"></input>
                                </div>
                                <div class="modal-footer">
                                    <div>
                                        <button type="submit" class="btn btn-general btn-white">Register</button>
                                    </div>
                                    <div>
                                        <button id="register_login_btn" type="button" class="btn btn-link">Accedi</button>
                                    </div>
                                </div>
                                </form>
                        </div>
                    </div>
                </div>
          </div>
      </div>
        );
    }
}
