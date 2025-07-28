import React from "react";
import { useParams } from "react-router-dom";
import trainersMock from "../TrainersMock";

function TrainerDetail() {
    const { id } = useParams();
    const trainer = trainersMock.find(t => t.trainerId === id);

    if (!trainer) {
        return <div>Trainer not found!</div>;
    }

    return (
        <div>
            <h2>Trainer Details</h2>
            <div>
                <h3>{trainer.name}</h3>
                <p> {trainer.trainerId}</p>
                <p> {trainer.email}</p>
                <p> {trainer.phone}</p>
                <ul>
                    {trainer.skills.map((skill, index) => (
                        <li key={index}>{skill}</li>
                    ))}
                </ul>
            </div>
        </div>
    );
}

export default TrainerDetail;