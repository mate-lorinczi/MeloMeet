import ConcertForm from "../components/ConcertForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const postNewVenue = async(values) => {
    console.log(values)
    const res = await fetch(
        '/api/events', 
        {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(values)
        }
        );
    const readRes = await res.json();
    console.log(readRes);
}

const NewEvent = () => {

    const [result, loading] = useAsyncFetch('/api/styles');
    const [venues, venuesLoading] = useAsyncFetch('/api/venues/all');

    const handleSubmit = async(values) => {
        postNewVenue({...values, "createdBy" : window.localStorage.getItem("userId")});
    }

    if(loading || venuesLoading) {
        return <div>Loading...</div>
    }

    return ( 
        <ConcertForm styles={result} venues={venues} submit={handleSubmit}/>
    );
}
 
export default NewEvent;