import { useState } from "react";
import UsernamePasswordFields from "../components/UsernamePassword";

const postLogin = async (loginData, setLoginMessage, setUserId) => {
  const res = await fetch("/api/users/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  });

  if (!res.ok) {
    return setLoginMessage("Username or password is invalid!");
  }

  //TODO: Login - rework setting userId in localstorage!

  const id = await res.text();
  window.localStorage.setItem("userId", id);
  setLoginMessage("Successfully logged in!");
  setUserId(id);
  return id;
};

const Login = () => {

  const SUBMIT_NAME = "Login";
  const [loginMessage, setLoginMessage] = useState(null);
  const [userId, setUserId] = useState(window.localStorage.getItem("userId"));

  const handleLogin = async (loginData) => {
    if (window.localStorage.getItem("userId")) {
      return setLoginMessage("Already logged in!");
    }

    const res = await postLogin(loginData, setLoginMessage, setUserId);

    console.log(res)
  };

  const handleLogout = () => {
    window.localStorage.removeItem("userId");
    setUserId(null);
  }

  return (
    <div>
      <UsernamePasswordFields submitName={SUBMIT_NAME} submit={handleLogin} />
      {userId ? <button onClick={handleLogout}>Logout</button> : null}
      {loginMessage ? <div>{loginMessage}</div> : loginMessage}
    </div>
  );
};

export default Login;
