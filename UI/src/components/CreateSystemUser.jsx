import React, {useRef} from 'react';
import Button from "@material-ui/core/button";


const CreateSystemUser = () => {

    const inputLogin = useRef(null);
    const inputPassword = useRef(null);

    const submit = (event) => {
        event.preventDefault();

        const login = inputLogin.current.value;
        const password = inputPassword.current.value;

        console.log("fetching: login: ", login, ",  password: ", password);

        const url = "/users/create";

        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(
                {
                    login: login,
                    password: password
                }
            )
        }).then(r => r.json().then(r => console.log(r)));

        console.log(`login: ${login}, password: ${password}`);
    }


    return (

        <div>
            <h5>Создавайте любых других SystemUsers под разрешением текущего пользователя</h5>
            <form onSubmit={submit}>
                <label>login
                    <input ref={inputLogin} name="login" type="text"
                           placeholder="input new login"/></label><br/>
                <label>password
                    <input ref={inputPassword} name="password" type="password"
                           placeholder="input new password"/></label><br/>
                submit <Button type="submit" color="primary" variant="contained">submit</Button>
            </form>
        </div>
    );
};

export default CreateSystemUser;