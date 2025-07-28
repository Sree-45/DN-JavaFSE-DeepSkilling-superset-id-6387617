import React from "react";
import { Link } from "react-router-dom";
import trainersMock from "../TrainersMock";

function TrainerList() {
    return(
        <>
            <div>
                <h2>Trainers List</h2>
                <div>
                <ul>
                    {trainersMock.map((trainer) => (
                        <li key={trainer.trainerId}>
                            <Link to={`/trainers/${trainer.trainerId}`}>
                                {trainer.name}
                            </Link>
                        </li>
                    ))}
                </ul>
                </div>
            </div>
        </>
    )
}

export default TrainerList;
