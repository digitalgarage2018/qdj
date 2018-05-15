import React from "react";

export default class FunzioniStudente extends React.Component{
    render(){
        return(
            
            //${requestScope['student'].name} ${requestScope['student'].surname} 
            //${requestScope['student'].badgeNumber
            <div>
                <h1>Informazioni studente:</h1>
                <ul>
                    <li>Matricola:</li> ${requestScope['user'].id_user}
                    <li>Nome: ${requestScope['user'].name}</li>
                    <li>Cognome: ${requestScope['user'].surname}</li>
                    <li>Email Istituzionale: ${requestScope['user'].istitutional_email}</li>
                    <li>Email Personale: ${requestScope['user'].personal_email}</li>
                    <li>Data di Nascita: ${requestScope['user'].date_of_birth}</li>
                </ul>
            </div>
        );   
    }                     
}