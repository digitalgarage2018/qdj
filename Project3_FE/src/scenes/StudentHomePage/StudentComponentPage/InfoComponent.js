import React from 'react';
import {Button} from "../../../components/Button/Button";
import {Input} from "../../../components/Input/Input";
import axios from 'axios';
import { UserInfo } from '../../../components/UserInfo/UserInfo';

export const InfoComponent = (props) => {

    return (
        <div>
            <UserInfo 
                type="STUDENTE" 
                name={props.name.toUpperCase()} 
                surname={props.surname.toUpperCase()} 
                matricola={props.matricola} 
                institutional_email={props.institutional_email}
                personal_email={props.personal_email}>
            </UserInfo>
        </div>
    )
};