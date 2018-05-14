import React, { Component } from 'react';
import { BrowserRouter, Route, Switch}from'react-router-dom';
import MainTemplate from "./components/mainLayout/template/mainTemplate";
import Prova from "./components/home/prova";
class App extends Component {

  constructor(props){
    super(props);
  }
  render() {
      return (
          <BrowserRouter>
            <MainTemplate>
              <Switch>
                <Route exact path = '/' component = {Prova}/>
              </Switch>
            </MainTemplate>
          </BrowserRouter>               
     );
 }
}

export default App;
