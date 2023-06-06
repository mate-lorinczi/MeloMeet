import { useState } from "react";

const GroupForm = (props) => {
  const [prefix, setPrefix] = useState("");
  const { events } = props;
  const [values, setValues] = useState({
    "openToNonFriends": false,
    "eventId": ""
  });

  const handleChange = (prop, value) => {
    const newValues = { ...values, [prop]: value };
    setValues(newValues);
  };

  const handleSubmit = () => {
    props.submit(values);
  }


  return (
    <div>
      <div className="event-search-bar">
        <input
          type="text"
          name="event-search-bar"
          value={prefix}
          placeholder="Search..."
          onChange={(e) => setPrefix(e.target.value)}
        />
        {prefix !== ""
          ? events
              .filter((e) => e.eventName.includes(prefix))
              .map((event) => (
                <div
                  key={event.eventId}
                  onClick={() => {handleChange("eventId", event.eventId); setPrefix(event.eventName)}}
                  value={event.eventId}
                >
                  {event.eventName}
                </div>
              ))
          : null}
      </div>
      <div>
        <input
          type="checkbox"
          name="newGroupIsOpen"
          onChange={() => handleChange("openToNonFriends", !values.isOpen)}
        />
        <label htmlFor="newGroupIsOpen">Open Air</label>
      </div>
      <button onClick={() => handleSubmit()}>Submit</button>
    </div>
  );
};

export default GroupForm;
