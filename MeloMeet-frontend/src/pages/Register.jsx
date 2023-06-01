import UsernamePasswordFields from "../components/UsernamePassword";

const postRegistration = async (regData) => {
  const res = await fetch("api/users", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(regData),
  });

  if (!res.ok) {
    console.log(res);
  }

  return await res.json();
};

const Register = () => {
  const SUBMIT_NAME = "Register";
  const handleRegistration = async (regData) => {
    const res = await postRegistration(regData);

    console.log(res);
  };

  return (
    <div>
      <div className="email">
        <label htmlFor="email">Email:</label>
        <input type="email" name="email" />
      </div>
      <UsernamePasswordFields
        submitName={SUBMIT_NAME}
        submit={handleRegistration}
      />
    </div>
  );
};

export default Register;
