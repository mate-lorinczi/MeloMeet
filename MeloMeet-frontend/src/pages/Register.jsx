import { useState } from "react";
import UsernamePasswordFields from "../components/UsernamePassword";

const getRegStatus = (code) => {
    const statuses = {
        201: "Successful registration!",
        409: "Username or Email already in use!"
    };

    return statuses[code];
}

const postRegistration = async (regData, setRegStatus) => {
  console.log(regData);  
  const res = await fetch("/api/users", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(regData),
  });

  if (!res.ok) {
    setRegStatus(getRegStatus(res.status));
    return;
  }
  setRegStatus(getRegStatus(res.status));
  return await res.json();
};

const Register = () => {
  const SUBMIT_NAME = "Register";

  const [values, setValues] = useState({"email": ""});
  const [regStatus, setRegStatus] = useState(null);

  const handleRegistration = async (regData) => {
    const res = await postRegistration({...values, ...regData}, setRegStatus);

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
      <div>{regStatus}</div>
    </div>
  );
};

export default Register;
