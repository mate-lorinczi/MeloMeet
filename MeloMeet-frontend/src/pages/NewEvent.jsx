import ConcertForm from "../components/ConcertForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const NewEvent = () => {

    const [result, loading] = useAsyncFetch('/api/styles');
    const [venues, venuesLoading] = useAsyncFetch('/api/venues/all');

    if(loading || venuesLoading) {
        return <div>Loading...</div>
    }

    return ( 
        <ConcertForm styles={result} venues={venues}/>
    );
}
 
export default NewEvent;