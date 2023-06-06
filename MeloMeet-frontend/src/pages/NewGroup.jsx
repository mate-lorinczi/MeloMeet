import GroupForm from "../components/GroupForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const NewGroup = () => {

    const [events, loading] = useAsyncFetch('/api/events/all');

    if(loading) {
        return <div>Loading...</div>
    }

    return ( 
        <GroupForm events={events}/>
     );
}
 
export default NewGroup;