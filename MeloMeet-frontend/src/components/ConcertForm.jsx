import { useState } from "react";

const ConcertForm = (props) => {
  
  const [values, setValues] = useState({
    "startDateAndTime" : "",
    "endDateAndTime" : "",
    "performers" : [],
    "styles" : [],
    "currPerformer" : "",
    "currStyle": "",
    "venue" : ""
  });
  const [styles, setStyles] = useState(props.styles);
  let indexMap = {};
  const {venues} = props;

  const handleSubmit = () => {
    const formatedValues = {...values};
    delete formatedValues["currStyle"];
    delete formatedValues["currPerformer"];

    console.log(formatedValues);
  }

  const addPerformer = () => {
    if(!values.performers.includes(values.currPerformer)) {
        const newPerformer = [...values.performers];
        newPerformer.push(values.currPerformer)
        setValues({...values, "performers": newPerformer, "currPerformer" : ""});
    }
  }

  const addStyle = () => {
    if(!values.styles.includes(values.currStyle) && values.currStyle !== "") {
      const newStyle = [...values.styles];
      newStyle.push(values.currStyle);


      const updatedStyles = [...styles];
      updatedStyles.splice(indexMap[values.currStyle], 1);
      setStyles(updatedStyles);
      indexMap = {}
      setValues({...values, "styles": newStyle, "currStyle" : ""});
      
    }
    
  }

  const handleChange = (prop) => (event) => {
    const newValues = {...values, [prop] : event.target.value};
    setValues(newValues);
  }

  return (
    <div>
      <div className="startTime">
        <label htmlFor="startTime">Choose start time:</label>
        <input type="datetime-local" name="startTime" onChange={handleChange("startDateAndTime")}/>
      </div>
      <div className="endTime">
        <label htmlFor="endTime">Choose end time:</label>
        <input type="datetime-local" name="endTime" onChange={handleChange("endDateAndTime")}/>
      </div>
      <div className="performer">
        <label htmlFor="performer">Add performer:</label>
        <input type="text" name="performer" onChange={handleChange("currPerformer")} value={values.currPerformer}/>
        <button onClick={() => addPerformer()}>Add</button>
      </div>
      <div className="styles">
        <label htmlFor="styles">Add a style: </label>
        <select name="styles" id="style-select" onClick={handleChange("currStyle")}>
          <option value="" selected="selected">Please choose an option</option>
            {styles.map((style, index) => {
                if(!values.styles.includes(style)) {
                    indexMap[style] = index;
                    return <option key={index} value={style.name}>{style.name}</option>;
                }
                })}
          
        </select>
        <button onClick={() => addStyle()}>Add Style</button>
      </div>
      <div className="venues">
        <label htmlFor="venues">Select a venue: </label>
        <select name="venues" id="style-select" onClick={handleChange("venue")}>
          <option value="" selected="selected">Please choose an option</option>
          {venues.map(venue => <option key={venue.venueId} value={venue.venueId}>{venue.venueName}</option>)}          
        </select>
      </div>
      <button onClick={handleSubmit}>Submit</button>
    </div>
  );
};

export default ConcertForm;
