import React from "react";

export default class FunzioniStudente extends React.Component{
    render(){
        return(
            <li className="nav-item dropdown" >
                <a className="nav-link dropdown-toggle smooth-scroll" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Menu</a> 
                <div className="dropdown-menu dropdown-cust" aria-labelledby="navbarDropdownMenuLink">
                    <a className="dropdown-item">Visualizza Libretto</a>
                    <a className="dropdown-item">Segui Lezioni Online</a>
                </div>
            </li>

        );   
    }                     
}