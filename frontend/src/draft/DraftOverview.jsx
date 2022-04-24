import {Component} from "react";

class DraftOverview extends Component {

    constructor(props) {
        super(props);
        this.state = {
            drafts: [],
            participants: "",
            newDraftName: ""
        };
        this.create = this.create.bind(this);
        this.handleChange = this.handleChange.bind(this);
        this.fetchDrafts = this.fetchDrafts.bind(this);
    }

    componentDidMount() {
        this.fetchDrafts()
    }

    fetchDrafts() {
        fetch('/drafts')
            .then(response => response.json())
            .then(data => this.setState({drafts: data}));
    }

    create() {
        fetch(`/drafts`, {
            method: 'POST',
            body: JSON.stringify({
                "name": this.state.newDraftName,
                "playerNames": this.state.participants.split(",")
            }),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            this.fetchDrafts()
        });
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        const draftList = this.state.drafts.map(draft => {
            return <tr key={draft.id}>
                <td style={{whiteSpace: 'nowrap'}}>{draft.id}</td>
                <td style={{whiteSpace: 'nowrap'}}>{draft.name}</td>
                <td style={{whiteSpace: 'nowrap'}}>{draft.players.map(
                    player => player.name
                ).join(", ")}</td>
            </tr>
        });

        return (
            <div>
                <h2>Drafts</h2>
                <table id={"players"}>
                    <tbody>
                    <tr>
                        <th>Id</th>
                        <th>Navn</th>
                        <th>Deltagere</th>
                    </tr>
                    {draftList}
                    </tbody>
                </table>
                <form>
                    <label>
                        Navn:
                        <input type="text" name={"newDraftName"} value={this.state.newDraftName} onInput={this.handleChange}/>
                        Deltagere:
                        <input type="text" name={"participants"} value={this.state.participants} onInput={this.handleChange}/>
                    </label>
                    <input type="submit" value="Submit" onClick={() => this.create()}/>
                </form>
            </div>
        )
    }
}

export default DraftOverview