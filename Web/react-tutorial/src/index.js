import React from "react"
import ReactDOM from "react-dom"
import './index.css'




class Login extends React.Component{

    constructor(props){
        super(props);
        this.state={entries: ["Nome","Password"]};
    }

    createEntry(entry){
        return(
            <div className="entry" key={this.state.entries.indexOf(entry)}>
                <label>Inserisci {entry}</label>
                <input id={entry}/>
            </div>
        )
    }

    handleSend(){
        this.state.entries.forEach(element => {
            console.log(document.getElementById(element.));
        });
    }


    render() {
        return (
            <div id="form">
                <h1>Login page</h1>
                <form>
                    {this.state.entries.map((entry)=>this.createEntry(entry))}
                </form>
                <button type="submit" onClick={this.handleSend}>Login</button>
            </div>
        );
    }

}

ReactDOM.render(<Login />, document.getElementById("root"));