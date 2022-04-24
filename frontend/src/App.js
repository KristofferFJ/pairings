import React, {Component} from 'react';
import './App.css';
import Home from './Home';
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Players from "./players/Players";
import AppNavbar from "./AppNavbar";
import DraftOverview from "./draft/DraftOverview";
//import ClientList from './ClientList';
//import ClientEdit from "./ClientEdit";
//                    <Route path='/clients/:id' component={ClientEdit}/>

class App extends Component {
    render() {
        return (

            <BrowserRouter>
                <AppNavbar/>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/players' exact={true} component={Players}/>
                    <Route path='/drafts' exact={true} component={DraftOverview}/>
                </Switch>
            </BrowserRouter>
        )
    }
}

export default App;