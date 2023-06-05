import { useState } from "react";

const ConcertForm = (props) => {

  const {styles} = props;
  const [values, setValues] = useState({
    "startDateAndTime" : "",
    "endDateAndTime" : "",
    "performers" : [],
    "styles" : [],
    "currPerformer" : "",
    "currStyle": ""
  });

  const addPerformer = () => {
    if(!values.performers.includes(values.currPerformer)) {
        const newPerformer = [...values.currPerformer];
        newPerformer.push(values.currPerformer)
        setValues({...values, "performers": newPerformer, "currPerformer" : ""})
    }
  }

  const addStyle = () => {
    const newStyle = [...values.currPerformer];
    newStyle.push(values.currStyle)
    setValues({...values, "styles": newStyle, "currStyle" : ""})
  }

  const handleChange = (prop) => (event) => {
    const newValues = {...values, [prop] : event.target.value};
    setValues(newValues);
  }

  return (
    <div>
      <div className="startTime">
        <label htmlFor="startTime">Choose start time:</label>
        <input type="datetime-local" name="startTime" onChange={handleChange("startTime")}/>
      </div>
      <div className="endTime">
        <label htmlFor="startTime">Choose end time:</label>
        <input type="datetime-local" name="endTime" onChange={handleChange("endTime")}/>
      </div>
      <div className="performer">
        <label htmlFor="performer">Add performer:</label>
        <input type="text" name="performer" onChange={handleChange("currPerformer")}/>
        <button onClick={addPerformer()}>Add</button>
      </div>
      <div className="styles">
        <label htmlFor="styles">Add a style: </label>
        <select name={styles} id="style-select" onClick={handleChange("currStyle")}>
            <option value="">Please choose an option</option>
            {styles.map(style => {
                if(!values.styles.includes(style)) {
                    <option key={style.styleId} value={style.name}>{style.name}</option>
                }
                })}
        </select>
        <button onClick={addStyle()}>Add Style</button>
      </div>
    </div>
  );
};

export default ConcertForm;
