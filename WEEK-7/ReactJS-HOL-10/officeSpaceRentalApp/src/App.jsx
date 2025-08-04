import React from "react";

function App() {
  const element = "Office Space"
  const jsxatt = <img src={'/images/image.png'} width="25%" height="25%" alt="Office Space"/>;
  const ItemName = {Name: "DBS", Rent: 50000, Address: "Chennai"};
  const officeSpaces = [
    {Name: "DBS", Rent: 50000, Address: "Chennai"},
    {Name: "Tech Hub", Rent: 75000, Address: "Bangalore"},
    {Name: "Business Center", Rent: 45000, Address: "Mumbai"},
    {Name: "Corporate Plaza", Rent: 80000, Address: "Delhi"}
  ];  
  const getRentColor = (rent) => {
    return rent <= 60000 ? 'red' : 'green';
  };

  return (
    <div>
      <h1>{element}, at Affordable Range</h1>
      {jsxatt}
      <div>
        <h1>Name: {ItemName.Name}</h1>
        <h3 style={{color: getRentColor(ItemName.Rent)}}>
          Rent: Rs. {ItemName.Rent}
        </h3>
        <h3>Address: {ItemName.Address}</h3>
      </div>
      <hr />

      <div>
        <h2>All Office Spaces</h2>
        {officeSpaces.map((office, index) => (
          <div key={index}>
            <h3>Name: {office.Name}</h3>
            <h4 style={{color: getRentColor(office.Rent)}}>
              Rent: Rs. {office.Rent}
            </h4>
            <h4>Address: {office.Address}</h4>
            <hr />
          </div>
        ))}
      </div>

    </div>
  )

}
export default App;