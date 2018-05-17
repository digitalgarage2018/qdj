import React, {Component} from 'react';
import {AppRouter} from "./AppRouter";
import {NavBar} from "./components/Navbar/Navbar";
import {Footer} from "./components/Footer/Footer";
import './App.css';


class App extends Component {
    
    render() {
        return (
            <div className="jumbotron">
                <NavBar/>
                <div className="container">
                    <div className="col-sm-8 col-sm-offset-2">
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
