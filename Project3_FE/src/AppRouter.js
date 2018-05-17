import React from 'react';
import FirstPage from "./scenes/FirstPage/FirstPage";
import SecondPage from "./scenes/SecondPage/SecondPage";
import {Route,Router, } from 'react-router-dom';


export const AppRouter = () => {
    return (
        <div>
            <Route exact path="/" component={FirstPage}/>
            <Route path="/second" component={SecondPage}/>
        </div>
    )
};