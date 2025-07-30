import React from 'react';
function PlayersLess70({players}) {
    return (
        <>
            <div>
                <h1>List of players having scores less than 70</h1>
                <ul>
                    {players.filter(player => player.score < 70).map((player, index) => (
                        <li key={index}>
                            {player.name} - score: {player.score}
                        </li>
                    ))}
                </ul>

            </div>
        </>
    )
}

export default PlayersLess70;