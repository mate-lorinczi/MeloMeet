import { useState } from "react";

const VenueForm = () => {

  const [values, setValues] = useState({
    "name" : "",
    "wholeAddress" : {
      "city" : "",
      "postalCode": "",
      "address": ""
    },
    "isOpenAir": false
  });

  const handleValueChange = (prop) => (event) => {
    let newValues;

    if(prop === "isOpenAir") {
      const isOpenAir = !values.isOpenAir;
      newValues = {...values, "isOpenAir":[isOpenAir]};
      
    } else {
      newValues = {...values, [prop] : event.target.value};
    }

    setValues(newValues);
  }

  return (
    <div>
      <div className="newVenueName">
        <label htmlFor="newVenueName">Name:</label>
        <input
          type="text"
          name="newVenueName"
        />
      </div>
      <div className="newVenueCity">
        <label htmlFor="newVenueCity">City</label>
        <input
          type="text"
          name="newVenueCity"
        />
      </div>
      <div className="newVenuePostalCode">
        <label htmlFor="newVenuePostalCode">Postal Code</label>
        <input
          type="text"
          name="newVenuePostalCode"
        />
      </div>
      <div className="newVenueAddress">
        <label htmlFor="newVenueAddress">Address</label>
        <input
          type="text"
          name="newVenueAddress"
        />
      </div>
      <div>
       <input type="checkbox" name="newVenueIsOpenAir"/>
       <label htmlFor="newVenueIsOpenAir">Open Air</label>
      </div>
    </div>
  );
};

export default VenueForm;
