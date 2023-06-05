import ConcertForm from "../components/ConcertForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const NewEvent = () => {

    const [result, loading] = useAsyncFetch('/api/styles');

    if(loading) {
        return <div>Loading...</div>
    }

    return ( 
        <ConcertForm styles={result}/>
    );
}
 
export default NewEvent;