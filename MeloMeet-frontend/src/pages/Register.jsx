import { useState } from "react";
import UsernamePasswordFields from "../components/UsernamePassword";

const postRegistration = async (regData) => {
  console.log(regData);  
  const res = await fetch("/api/users", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(regData),
  });

  if (!res.ok) {
    console.log(res.status);
    return res.status
  }

  return await res.json();
};

const Register = () => {
  const SUBMIT_NAME = "Register";

  const [values, setValues] = useState({"email": ""})

  const handleRegistration = async (regData) => {
    const res = await postRegistration({...values, ...regData});

    console.log(res);
  };

  const handleValueChange = (prop) => (event) => {
    const newValues = {...values, [prop] : event.target.value};
    setValues(newValues);
  }

  return (
    <div>
      <div className="email">
        <label htmlFor="email">Email:</label>
        <input type="email" name="email" onChange={handleValueChange("email")}/>
      </div>
      <UsernamePasswordFields
        submitName={SUBMIT_NAME}
        submit={handleRegistration}
      />
    </div>
  );
};

export default Register;
