import React from 'react';
import {Button} from "../../../components/Button/Button";
import {Input} from "../../../components/Input/Input";
import axios from 'axios';
import { Exam } from '../../../components/Exam/Exam';
import { UserInfo } from '../../../components/UserInfo/UserInfo';

export const InfoComponent = (props) => {

    const onClick = () => {
        console.log(this);
    };

    const onClickPrint = () => {

    };

    const outputToFather = (e) => {
        props.output(e);
    };

    return (
        <div>
            <UserInfo name={props.name} surname={props.surname} matricola={props.matricola} institutional_email={props.institutional_email}></UserInfo>
        </div>
    )
};