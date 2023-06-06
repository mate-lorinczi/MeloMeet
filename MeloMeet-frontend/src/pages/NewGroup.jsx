import GroupForm from "../components/GroupForm";
import { useAsyncFetch } from "../hooks/useAsyncFetch";

const postGroup = async(values) => {
    console.log(JSON.stringify(values))
    const res = await fetch('/api/groups', {
        method: 'POST',
        headers: {
            'Content-Type' : 'application/json'
        },
        body: JSON.stringify(values)
    });
    const resRead = await res.json();

}

const NewGroup = () => {

    const [events, loading] = useAsyncFetch('/api/events/all');
    const submit = (values) => {
        const userId = localStorage.getItem("userId");
        const finalValues = {"creatorId" : userId, ...values}
        postGroup(finalValues);
    }

    if(loading) {
        return <div>Loading...</div>
    }

    return ( 
        <GroupForm events={events} submit={submit}/>
     );
}
 
export default NewGroup;