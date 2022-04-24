import logo from './logo.svg';
import './App.css';
import {Component} from "react";
import Players from "./players/Players";

class App extends Component {
    state = {
        players: []
    };

    async componentDidMount() {
        const response = await fetch('/players');
        const body = await response.json();
        this.setState({players: body});
    }

    render() {
        const players = this.state.players;
        return (
            <div className="App">
                <h2>Players</h2>
                {players && players.length > 0 &&
                    <Players players={players}/>
                }
            </div>
        );
    }
}

export default App;
