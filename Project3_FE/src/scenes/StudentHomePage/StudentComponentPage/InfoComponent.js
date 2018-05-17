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
            <UserInfo></UserInfo>
            <Button title='example' customOnClick={() => onClick()}/>
            <Input type='text' customOnChange={(e) => outputToFather(e)}/>
            <Button title='cliccami' customOnClick={() => onClickPrint()}/>
            <Exam name="Economia" description="alskdnfasdnksanlksnvldsnvlksads" credits="8"></Exam>
        </div>
    )
};