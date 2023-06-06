import { useState } from "react";

const GroupForm = (props) => {
  const [prefix, setPrefix] = useState("");
  const { events } = props;
  const [values, setValues] = useState({
    eventId: "",
    isOpen: false,
  });

  const handleChange = (prop, value) => {
    const newValues = { ...values, [prop]: value };
    setValues(newValues);
  };

  const handleSubmit = () => {
    props.submit(values);
  }

  console.log(events);

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
          name="newVenueIsOpenAir"
          onChange={() => handleChange("isOpenAir", !values.isOpen)}
        />
        <label htmlFor="newVenueIsOpenAir">Open Air</label>
      </div>
      <button onClick={() => handleSubmit()}>Submit</button>
    </div>
  );
};

export default GroupForm;
