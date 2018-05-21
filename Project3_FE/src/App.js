import React, {Component} from 'react';
import {AppRouter} from "./AppRouter";
import {NavBar} from "./components/Navbar/Navbar";
import {Footer} from "./components/Footer/Footer";
import './App.css';


class App extends Component {

    componentDidMount(){
        sessionStorage.setItem('type',"N");
    }

    render() {
        return (
            <div className="jumbotron">
                <NavBar type={sessionStorage.getItem("type")}/>
                <div className="container">
                    <div >
                        {alert.message &&
                            <div className={`alert ${alert.type}`}>{alert.message}</div>
                        }
                        <div>
                            <AppRouter/>
                            <Footer/>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default App;
