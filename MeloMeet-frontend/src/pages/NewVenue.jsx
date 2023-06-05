import { useState } from "react";
import VenueForm from "../components/VenueForm";

const postNewVenue = async (values) => {
    const res = await fetch('/api/venues', {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify(values)
    });

    if(!res.ok) {
        console.log(res);
        return res.status;

    }

    console.log(await res.json())
    return res.status;
}

const NewVenue = () => {

    const statusMap = {
        201: "Venue added!",
        409: "Venue already added, or bad address!"
    }
    const [statusText, setStatusText] = useState(null)

    const handleSubmit = async (values) => {

        const res = await postNewVenue(values);
        
        setStatusText(statusMap[res]);
    }

    return ( <div>
        <VenueForm submit={handleSubmit} />
        {statusText ? <div>{statusText}</div> : null}
    </div> );
}
 
export default NewVenue;