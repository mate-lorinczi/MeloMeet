import { useState } from "react";

const GroupForm = (props) => {

    const [prefix, setPrefix] = useState("");
    const {events} = proprs;

    return ( <div>
        <input 
            type="text"
            name="event-search-bar"
            value={prefix}
            placeholder="Search..."
        />
        {events.filter(e => e.includes(prefix)).map(event => <div key={event.eventId} value={event.eventId}>{event.eventName}</div>)}
    </div> );
}
 
export default GroupForm;