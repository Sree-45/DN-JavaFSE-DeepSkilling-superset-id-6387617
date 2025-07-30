import React from 'react';
function EvenTeamPlayers({players}) {
    const EvenTeamPlayers = []
    players.forEach((player, index) => {
        if((index % 2 == 0)) {
            EvenTeamPlayers.push(player);
        }
    });
    return (
        <>
            <div>
                <h1>Even Team Players</h1>
                <ul>
                    {EvenTeamPlayers.map((player, index) => (
                        <li key={index}>
                            {player.name} - Score: {player.score}
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}

export default EvenTeamPlayers;