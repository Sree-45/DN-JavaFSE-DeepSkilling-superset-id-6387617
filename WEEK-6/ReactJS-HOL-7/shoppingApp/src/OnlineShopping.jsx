import React, { Component } from "react";
import Cart from "./Cart";

class OnlineShopping extends Component {
    render() {
        const CartInfo = [
            {itemname:"Laptop", price: 80000},
            {itemname:"Mobile", price: 30000},
            {itemname:"Tablet", price: 20000},
            {itemname:"Headphones", price: 5000},
            {itemname:"Smartwatch", price: 15000}
        ];
        
        return (
            <div className="mydiv">
                <h1>Items Ordered :</h1>
                <table border={1}>
                    <thead>
                        <tr>
                            <th>Item Name</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <Cart item={CartInfo} />
                    </tbody>
                </table>
            </div>
        );
    }
}

export default OnlineShopping;