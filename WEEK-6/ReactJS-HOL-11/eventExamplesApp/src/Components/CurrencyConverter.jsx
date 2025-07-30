import React, { useState } from "react";

function CurrencyConverter() {
    const [rupees, setRupees] = useState(0);
    const conversionRate = 90;
    
    const handleSubmit = (event) => {
        event.preventDefault();        
        const euroAmount = (rupees / conversionRate).toFixed(2);
        alert(`₹${rupees} Indian Rupees = €${euroAmount} Euros`);
    }    
    const handleInputChange = (event) => {
        setRupees(event.target.value);
    }
    
    return (
        <div>
            <h2>Currency Converter - INR to Euro</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Enter amount in Indian Rupees:
                    <input 
                        type="number" 
                        value={rupees}
                        onChange={handleInputChange}
                        placeholder="Enter INR amount"
                        min="0"
                    />
                </label>
                <br /><br />
                <button type="submit" onClick={handleSubmit}>Convert</button>
            </form>
        </div>
    );
}

export default CurrencyConverter;