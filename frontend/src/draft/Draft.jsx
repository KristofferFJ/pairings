import {Component} from "react";
import {Button} from "reactstrap";

class DraftOverview extends Component {

    constructor(props) {
        super(props);
        this.state = {
            draftId: props.match.params.draftId,
            draftName: "",
            matches: [],
            standings: []
        };

        this.fetchDraft = this.fetchDraft.bind(this)
        this.startNextRound = this.startNextRound.bind(this)
    }

    componentDidMount() {
        this.fetchDraft(this.state.draftId)
    }

    fetchDraft() {
        fetch('/drafts/' + this.state.draftId + '/standings')
            .then(response => response.json())
            .then(data => this.setState({
                draftName: data.draftName,
                matches: data.matches,
                standings: data.standings
            }));
    }

    startNextRound() {
        fetch('/drafts/' + this.state.draftId + '/start-next-round',
            {method: 'POST'})
            .then(data => this.fetchDraft(this.state.draftId));
    }

    render() {
        const matches = this.state.matches.map(match => {
            return <tr key={match.matchId}>
                <td style={{whiteSpace: 'nowrap'}}>{match.player1Id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{match.player2Id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{match.player1Rounds}</td>
                <td style={{whiteSpace: 'nowrap'}}>{match.player2Rounds}</td>
            </tr>
        });

        const standings = this.state.standings.map(player => {
            return <tr key={player.playerId}>
                <td style={{whiteSpace: 'nowrap'}}>{player.playerId}</td>
                <td style={{whiteSpace: 'nowrap'}}>{player.playerName}</td>
                <td style={{whiteSpace: 'nowrap'}}>{player.points}</td>
            </tr>
        });

        return (
            <div>
                <h2>{this.state.draftName}</h2>
                <h3>Matches</h3>
                <table id={"players"}>
                    <tbody>
                    <tr>
                        <th>Player1</th>
                        <th>Player2</th>
                        <th>Player1 runder</th>
                        <th>Player2 runder</th>
                    </tr>
                    {matches}
                    </tbody>
                </table>
                <h3>Standings</h3>
                <table>
                    <tbody>
                    <tr>
                        <th>Spiller ID</th>
                        <th>Spillernavn</th>
                        <th>Point</th>
                    </tr>
                    {standings}
                    </tbody>
                </table>
                <h2>Start ny runde</h2>
                <Button size="sm" color="danger" onClick={this.startNextRound}>Ny runde</Button>
            </div>
        )
    }
}

export default DraftOverview