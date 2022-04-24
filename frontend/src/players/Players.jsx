import Player from "./Player";
import {Component} from "react";
import {Button, ButtonGroup} from "reactstrap";
import {Link} from "react-router-dom";

class Players extends Component {

    constructor(props) {
        super(props);
        this.state = {players: [], newPlayerName: ""};
        this.remove = this.remove.bind(this);
        this.create = this.create.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.fetchPlayers = this.fetchPlayers.bind(this);
    }

    componentDidMount() {
        this.fetchPlayers()
    }

    fetchPlayers() {
        fetch('/players')
            .then(response => response.json())
            .then(data => this.setState({players: data}));
    }

    remove(id) {
        fetch(`/players/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            this.fetchPlayers()
        });
    }

    create() {
        fetch(`/players`, {
            method: 'POST',
            body: JSON.stringify({"name": this.state.newPlayerName}),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            this.fetchPlayers()
        });
    }

    handleChange(event) {
        this.setState({newPlayerName: event.target.value});
    }

    render() {
        const playerList = this.state.players.map(player => {
            return <tr key={player.id}>
                <td style={{whiteSpace: 'nowrap'}}>{player.id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{player.name}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/players/" + player.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(player.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <h2>Players</h2>
                <table id={"players"}>
                    <tbody>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Actions</th>
                    </tr>
                    {playerList}
                    </tbody>
                </table>
                <form>
                    <label>
                        Name:
                        <input type="text" value={this.state.newPlayerName} onInput={this.handleChange}/>
                    </label>
                    <input type="submit" value="Submit" onClick={() => this.create(this.state.newPlayerName)}/>
                </form>
            </div>
        )
    }
}

export default Players