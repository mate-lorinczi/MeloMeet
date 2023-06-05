import { useState } from "react";

const VenueForm = (props) => {
  
  const [values, setValues] = useState({
    "name" : "",
    "wholeAddress" : {
      "city" : "",
      "postalCode": "",
      "address": ""
    },
    "isOpenAir": false
  });

  const handleSubmit = () => {
    props.submit(values)
  }

  const handleValueChange = (prop) => (event) => {
    let newValues;

    if(prop === "isOpenAir") {
      const isOpenAir = !values.isOpenAir;
      newValues = {...values, "isOpenAir":isOpenAir};
      
    } else {
      console.log(values["wholeAddress"]["city"])
      newValues = values[prop] !== undefined ? {...values, [prop] : event.target.value} : {...values, wholeAddress: {
        ...values.wholeAddress,
        [prop] : event.target.value
      }};
    }

    setValues(newValues);
    console.log(values);
  }

  return (
    <div>
      <div className="newVenueName">
        <label htmlFor="newVenueName">Name:</label>
        <input
          type="text"
          name="newVenueName"
          onChange={handleValueChange("name")}
        />
      </div>
      <div className="newVenueCity">
        <label htmlFor="newVenueCity">City</label>
        <input
          type="text"
          name="newVenueCity"
          onChange={handleValueChange("city")}
        />
      </div>
      <div className="newVenuePostalCode">
        <label htmlFor="newVenuePostalCode">Postal Code</label>
        <input
          type="text"
          name="newVenuePostalCode"
          onChange={handleValueChange("postalCode")}
        />
      </div>
      <div className="newVenueAddress">
        <label htmlFor="newVenueAddress">Address</label>
        <input
          type="text"
          name="newVenueAddress"
          onChange={handleValueChange("address")}
        />
      </div>
      <div>
       <input type="checkbox" name="newVenueIsOpenAir" onChange={handleValueChange("isOpenAir")}/>
       <label htmlFor="newVenueIsOpenAir">Open Air</label>
       
      </div>
      <button onClick={handleSubmit}>
        Submit
      </button>
    </div>
  );
};

export default VenueForm;
