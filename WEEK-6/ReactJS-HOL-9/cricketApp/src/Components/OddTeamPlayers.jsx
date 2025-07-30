import React from 'react';
function OddTeamPlayers({players}) {
    const oddTeamPlayers = []
    players.forEach((player, index) => {
        if((index % 2 != 0)) {
            oddTeamPlayers.push(player);
        }
    });
    return (
        <>
            <div>
                <h1>Odd Team Players</h1>
                <ul>
                    {oddTeamPlayers.map((player, index) => (
                        <li key={index}>
                            {player.name} - Score: {player.score}
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}

export default OddTeamPlayers;