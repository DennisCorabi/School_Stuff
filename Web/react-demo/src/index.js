import React from "react";
import ReactDOM from "react-dom";
import "./index.css";



class Amonga extends React.Component{
    constructor(props){
        super(props);
        this.state ={
            image:"https://loghi-famosi.com/wp-content/uploads/2021/08/Among-Us-Logo.png",
            message:"Amonga",
        };
    }

    updatePhoto(){
        if (this.state.image=="https://loghi-famosi.com/wp-content/uploads/2021/08/Among-Us-Logo.png"){
            console.log("sheesh");
            this.setState({image: "https://c.tenor.com/jUMex_rdqPwAAAAM/among-us-twerk.gif"});
        }
        else{
            console.log("ciao");
            this.setState({image: "https://loghi-famosi.com/wp-content/uploads/2021/08/Among-Us-Logo.png"});
        }
        this.setState({message:"Sommo"})

    }
    
    render(){
        return(
            <div id="amongus">
                <img src={this.state.image} onClick={this.updatePhoto}></img>
                <h1 id="text">{this.state.message}</h1>
            </div>
        );
    }
}


ReactDOM.render(
    <Amonga />, document.getElementById("root")
);