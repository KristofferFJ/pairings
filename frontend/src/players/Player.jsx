import {Component} from "react";

class Player extends Component {
    render() {
        return (
            <tr>
                <td>{this.props.player.id}</td>
                <td>{this.props.player.name}</td>
            </tr>
        )
    }
}

export default Player