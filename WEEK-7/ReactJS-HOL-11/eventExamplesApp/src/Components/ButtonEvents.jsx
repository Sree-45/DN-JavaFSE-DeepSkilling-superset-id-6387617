import React from "react";
import { useState } from "react";
import CurrencyConverter from "./CurrencyConverter";

function ButtonEvents() {
    const [count, setCount] = useState(0);
    const [name, setName] = useState("sreeshanth");
    const increase = () => {
        setCount(count + 1);
        alert(`Hello ${name}`);

    }
    const sayWelcome = (text) => {
        alert(`${text}`);
    }
    const handleOnPress = (event) => {
        console.log("synthetic event :", event);
        alert("I was Clicked");
    }
    return (
        <div>
            <h1>{count}</h1>
            <button onClick={increase}>increment</button><br />
            <button onClick={() => setCount(count - 1)}>decrement</button><br />
            <button onClick={() => sayWelcome("welcome")}>Say welcome</button><br />
            <button onClick={handleOnPress}>cick me</button>
            <CurrencyConverter />
        </div>
    )

}

export default ButtonEvents;