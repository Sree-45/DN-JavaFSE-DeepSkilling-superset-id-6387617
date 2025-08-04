import React, { useState } from "react";
import PlayersList from "../src/Components/PlayersList.jsx";
import PlayersLess70 from "./Components/PlayersLess70.jsx";
import OddTeamPlayers from "./Components/OddTeamPlayers.jsx";
import EvenTeamPlayers from "./Components/EvenTeamPlayers.jsx";

function PlayersPage() {
    const [flag, setFlag] = useState(false);
    
    const players = [
        { name: "Rohit sharma", score : 121},
        { name: "Shubman Gill", score : 89},
        { name: "Virat Kohli", score : 102},
        { name: "Shreyas Iyer", score : 75},
        { name: "KL Rahul", score : 65},
        { name: "hardik Pandya", score : 45},
        { name: "jadeja", score : 35},
        { name: "kuldeep Yadav", score : 15},
        { name: "Mohammed Shami", score : 10},
        { name: "Mohammed Siraj", score : 5},
        { name: "Jasprit Bumrah", score : 0}
    ]
     const T20players = [
        { name: "MS Dhoni", score: 95},
        { name: "Suresh Raina", score: 78},
        { name: "Rishabh Pant", score: 82},
        { name: "Dinesh Karthik", score: 56}
    ];

    const RanjiTrophyPlayers = [
        { name: "Cheteshwar Pujara", score: 134},
        { name: "Ajinkya Rahane", score: 87},
        { name: "Mayank Agarwal", score: 92},
        { name: "Prithvi Shaw", score: 67}
    ];

    const mergedPlayers = [...T20players, ...RanjiTrophyPlayers];

    const oddTeamPlayers = []
    const evenTeamPlayers = []

    players.forEach((player, index) => {
        if(index % 2 == 0) {
            evenTeamPlayers.push(player);
        } else {
            oddTeamPlayers.push(player);
        }
    });
    
    return (
        <>
            <div>
                <button onClick={() => setFlag(!flag)}>
                    {flag ? 'flag = false' : 'flag = true'}
                </button>
            </div>

            {flag && (
                <>
                    <PlayersList players={players} />
                    <PlayersLess70 players={players} />
                </>
            )}

            {!flag && (
                <>
                    <OddTeamPlayers players={players} />
                    <EvenTeamPlayers players={players} />

                    <div>
                        <h1>list of indian players merged</h1>
                        <ul>
                            {mergedPlayers.map((player, index) => (
                                <li key={index}>
                                    {player.name} - Score : {player.score}
                                </li>
                            ))  }
                        </ul>
                    </div>
                </>
            )}
        </>
    )
}

export default PlayersPage;