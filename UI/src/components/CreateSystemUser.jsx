import React, {useRef} from 'react';
import Button from "@material-ui/core/button";


const CreateSystemUser = () => {

    const inputLogin = useRef(null);
    const inputPassword = useRef(null);

    const submit = (event) => {
        event.preventDefault();

        const credentials = {
            login: inputLogin.current.value,
            password: inputPassword.current.value
    }

        const url = "/users/create";

        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(credentials)
        }).then(r => r.json().then(r => console.log(r)));
    }


    return (

        <div>
            <h5>Создавайте любых других SystemUsers под разрешением текущего пользователя</h5>
            <form onSubmit={submit}>
                <label>login
                    <input ref={inputLogin} name="login" defaultValue={"1"} type="text"
                           placeholder="input new login"/></label><br/>
                <label>password
                    <input ref={inputPassword} name="password" defaultValue={"1"} type="password"
                           placeholder="input new password"/></label><br/>
                submit <Button type="submit" color="primary" variant="contained">submit</Button>
            </form>
        </div>
    );
};

export default CreateSystemUser;