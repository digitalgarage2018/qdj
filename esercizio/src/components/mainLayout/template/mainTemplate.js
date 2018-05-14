import React from "react";
import Header from "../header/header";
import Footer from "../footer/footer";
import Prova from "../../home/prova";

export default class MainTemplate extends React.Component{
    constructor(props){
        super(props);
    }
    render(){
        return (
            <div>
            <Header/>
            <Prova/>
            <p>
	                <br></br>
	                <br></br>
	                <br></br>
	        </p>
            <Footer/>

            </div>
        );
    }
}