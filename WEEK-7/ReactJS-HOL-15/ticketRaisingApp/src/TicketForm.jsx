import React, { useState } from "react";
import "./TicketForm.css";

function TicketForm() {
    const [name, setName] = useState("")
    const [complaint, setComplaint] = useState("")
    
    const generateRandomId = () => {
        return Math.floor(Math.random() * 90000) + 10000;
    }
    
    const handleSubmit = (e) => {
        e.preventDefault();
        const complaintId = generateRandomId();
        const msg = `Thanks ${name}\nYour Complaint was Submitted. ID is: ${complaintId}`;
        alert(msg);
        
        setName("");
        setComplaint("");
    }

    return(
        <div className="ticket-form-container">
            <div className="ticket-form">
                <h1 className="form-title">Register your complaints here!!!</h1>
                <form onSubmit={handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="name" className="form-label">Name:</label>
                        <input 
                            placeholder="enter your name" 
                            name="name" 
                            id="name"
                            className="form-input"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="complaint" className="form-label">Complaint:</label>
                        <textarea 
                            placeholder="enter your complaint" 
                            name="complaint" 
                            id="complaint"
                            className="form-textarea"
                            value={complaint}
                            onChange={(e) => setComplaint(e.target.value)}
                        />
                    </div>
                    <div className="submit-container">
                        <button type="submit" className="submit-button">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    )
}

export default TicketForm;