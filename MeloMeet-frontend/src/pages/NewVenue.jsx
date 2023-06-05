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

    }

    return await res.json();
}

const NewVenue = () => {

    const handleSubmit = async (values) => {

        const res = await postNewVenue(values);

        console.log(res);
    }

    return ( <div>
        <VenueForm submit={handleSubmit} />
    </div> );
}
 
export default NewVenue;