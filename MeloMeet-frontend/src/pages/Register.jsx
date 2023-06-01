import UsernamePasswordFields from "../components/UsernamePassword";

const postRegistration = (regData) => {
    const res = await fetch('api/users')
}

const Register = () => {

    return ( 
        <div>
            <UsernamePasswordFields submitName="Register"/>
        </div>
     );
}
 
export default Register;