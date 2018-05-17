import React from 'react';
import   axios from 'axios';
import { InfoComponent } from './StudentComponentPage/InfoComponent';

export default class StudentHomePage extends React.Component {
    constructor(props) {
        super(props);
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleSubmit(e) {
        console.log(sessionStorage.getItem("user"));
    }

    render() {
        return (
            <div className="col-md-6 col-md-offset-3">
                <InfoComponent></InfoComponent>
            </div>
        );
    }
}