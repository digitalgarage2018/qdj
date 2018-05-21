import React from 'react';
import {Route,Router, } from 'react-router-dom';
import LoginPage from "./scenes/LoginPage/LoginPage";
import RegisterPage from "./scenes/RegisterPage/RegisterPage";
import StudentHomePage from "./scenes/StudentHomePage/StudentHomePage";
import ProfessorHomePage from './scenes/ProfessorHomePage/ProfessorHomePage';



export const AppRouter = () => {
    return (
        <div>
            <Route exact path="/" component={LoginPage}/>
            <Route path="/login" component={LoginPage}/>
            <Route path="/register" component={RegisterPage}/>
            <Route path="/studentHome" component={StudentHomePage}/>
            <Route path="/professorHome" component={ProfessorHomePage}/>
        </div>
    )
};