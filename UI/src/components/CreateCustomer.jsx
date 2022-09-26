import React, {useRef} from 'react';
import Button from "@material-ui/core/button";


const CreateCustomer = () => {

    const inputName = useRef(null);
    const inputEmail = useRef(null);
    const inputAge = useRef(null);

    const submit = (event) => {
        event.preventDefault();

        const name = inputName.current.value;
        const email = inputEmail.current.value;
        const age = inputAge.current.value;

        const url = "/customers";

        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(
                {
                    name: name,
                    email: email,
                    age: age
                }
            )
        }).then(r => r.json().then(r => console.log(r)));

        console.log(`name: ${name}, email: ${email}, age: ${age}`);
    }


    return (
        <form onSubmit={submit}>
            <label>name
                <input ref={inputName} name="name" type="text" placeholder="input customer name"/></label><br/>
            <label>email
                <input ref={inputEmail} name="email" type="text" placeholder="input customer email"/></label><br/>
            <label>age  
                <input ref={inputAge} name="age" type="number" placeholder="input customer age"/></label><br/>
            submit <Button type="submit" color="primary" variant="contained">submit</Button>
        </form>
    );
};

export default CreateCustomer;