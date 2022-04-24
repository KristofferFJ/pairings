import Player from "./Player";
import {Component} from "react";
import player from "./Player";

class Players extends Component {
    render() {
        const players = this.props.players
            .sort((a, b) => a.id - b.id)
            .map(player =>
            <Player player={player}/>
        );
        return (
            <table id={"players"}>
                <tbody>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                </tr>
                {players}
                </tbody>
            </table>
        )
    }
}

export default Players