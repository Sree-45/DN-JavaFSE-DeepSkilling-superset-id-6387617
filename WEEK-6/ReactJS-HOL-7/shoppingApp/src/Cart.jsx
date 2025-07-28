import React, {Component} from "react";

class Cart extends Component {
    constructor(props) {
        super(props);
        this.itemname = props.itemname || "";
        this.price = props.price || 0;
    }
    
    render() {
        return (
            <>
                {this.props.item.map((item, index) => {
                    return (
                        <tr key={index}> 
                            <td>{item.itemname}</td>
                            <td>{item.price}</td>
                        </tr>
                    )
                })}
            </>
        );
    }
}

export default Cart;