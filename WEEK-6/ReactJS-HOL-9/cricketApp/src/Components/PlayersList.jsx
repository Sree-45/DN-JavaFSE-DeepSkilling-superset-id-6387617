function PlyersList({players}) {
    return (
        <>
            <div>
                <h1>Players List</h1>
                <ul>
                    {players.map((player, index) => (
                        <li key={index}>
                            {player.name} - Score: {player.score}
                        </li>
                    ))}
                </ul>
            </div>
        </>
    )
}

export default PlyersList;