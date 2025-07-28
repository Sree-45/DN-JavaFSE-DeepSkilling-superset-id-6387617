import React from "react";
import { Component } from "react";

class CountPeople extends  Component {
    constructor(props) {
        super(props);
        this.state = {
            entryCount: 0,
            exitCount:0
        };
    }
    

    UpdateEntry = () => {
        this.setState(prevState => ({
            entryCount: prevState.entryCount + 1
        }));
    }

    UpdateExit = () => {
        this.setState(prevState  => ({
            exitCount: prevState.exitCount + 1
        }));
    }

    render() {
        return (
            <>
               <div style={{ textAlign: 'center', padding: '20px' }}>
                    <h2>Mall People Counter</h2>
            
                    <div style={{ margin: '20px 0' }}>
                        <h3>People who entered the mall: {this.state.entryCount}</h3>
                        <h3>People who exited the mall: {this.state.exitCount}</h3>
                    </div>
            
                    <div>
                        <button 
                            onClick={this.UpdateEntry}
                            style={{ margin: '10px', padding: '10px 20px' }}
                            >   
                        Login
                    </button>
              
                    <button 
                        onClick={this.UpdateExit}
                        style={{ margin: '10px', padding: '10px 20px' }}
                        >
                        Exit
                    </button>
                </div>
            
            
            </div> 
            </>
        );
    }

}
export default CountPeople;
