import UsernamePasswordFields from "../components/UsernamePassword";

const postLogin = async(loginData) => {
    const res = await fetch('/api/users/login', {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(loginData)
    })

    if(!res.ok) {
        console.log(res);
    }
    const id = await res.text();
    console.log(id);

    return id;
}

const Login = () => {
    const SUBMIT_NAME = "Login";
    const handleLogin = async(loginData) => {
        const res = await postLogin(loginData);

        console.log(res);
    }

    return ( 
        <UsernamePasswordFields submitName={SUBMIT_NAME} submit={handleLogin}/>
     );
}
 
export default Login;