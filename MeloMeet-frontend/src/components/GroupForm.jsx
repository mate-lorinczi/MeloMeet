import { useState } from "react";

const GroupForm = (props) => {

    const [prefix, setPrefix] = useState("");
    const {events} = props;

    console.log(events)

    return ( <div>
        <input 
            type="text"
            name="event-search-bar"
            value={prefix}
            placeholder="Search..."
            onChange={(e) => setPrefix(e.target.value)}
        />
        {prefix !== "" ? events.filter(e => e.eventName.includes(prefix)).map(event => <div key={event.eventId} value={event.eventId}>{event.eventName}</div>) : null}
    </div> );
}
 
export default GroupForm;