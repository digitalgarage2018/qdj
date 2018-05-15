import React from "react";
import Logo from "../../images/logoUniMarina.png";

export default class HeaderWelcome extends React.Component{
    render(){
        return(
            <nav className="navbar navbar-expand-lg navbar-light" id="mainNav" data-toggle="affix">
                <div className="container">
                    <a className="navbar-brand" >
                        <img src={Logo} className = "logo-image"/>
                    </a>
                    <h1> Università Telematica per eccellenza </h1>
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item" ><a class="nav-link smooth-scroll">Home</a></li>
                            <li className="nav-item" ><a class="nav-link smooth-scroll">Logout</a></li>
                        </ul>  
                    </div>
                </div>
            </nav>




        );   
    }                     

}