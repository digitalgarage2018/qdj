import React from 'react';
import {Button} from "../../../components/Button/Button";
import {Input} from "../../../components/Input/Input";
import axios from 'axios';
import { Exam } from '../../../components/Exam/Exam';

export const FirstComponent = (props) => {

    const onClick = () => {
        console.log(this);
    };

    const onClickPrint = () => {
       
        const requestOptions = {
            headers: { 'Content-Type': 'application/json',
                        'jwt':'asafwe43f234f342v3v33' },
            body : JSON.stringify({ email:'m.danaila@studenti.unimarina.it',
        pwd:'asd' })
        };
        axios.post('http://localhost:8070/loginController',requestOptions )
            .then((res) => console.log(res.data.message))
            .catch(err => console.error(err))
    };

    const outputToFather = (e) => {
        props.output(e);
    };

    return (
        <div>
            <Button title='example' customOnClick={() => onClick()}/>
            <Input type='text' customOnChange={(e) => outputToFather(e)}/>
            <Button title='cliccami' customOnClick={() => onClickPrint()}/>
            <Exam name="Economia" description="alskdnfasdnksanlksnvldsnvlksads" credits="8"></Exam>
        </div>
    )
};