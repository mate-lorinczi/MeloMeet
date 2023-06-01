import { useState } from "react";

const UsernamePasswordFields = (props) => {

  const {submitName} = props;

  const handleClick = () => {
    props.submit(values)
  }

  const [values, setValues] = useState({"username": "", "password": ""});

  const handleChange = (prop) => (event) => {
    const newValues = {...values, [prop]:event.target.value}
    setValues(newValues);
  }

  return (
    <div>
      <div className="username">
        <label htmlFor="username">Username:</label>
        <input type="text" name="username" onChange={handleChange("username")}/>
      </div>
      <div className="password">
        <label htmlFor="password">Password</label>
        <input type="password" name="password" onChange={handleChange("username")}/>
      </div>
      <button onClick={handleClick}>{submitName}</button>
    </div>
  );
};

export default UsernamePasswordFields;
