import React, {useRef} from 'react';
import Button from "@material-ui/core/button";


const CreateCustomer = () => {

    const inputName = useRef(null);
    const inputEmail = useRef(null);
    const inputAge = useRef(null);

    const submit = (event) => {
        event.preventDefault();

        const customerData = {
            name:  inputName.current.value,
            email: inputEmail.current.value,
            age:   inputAge.current.value
        } ;

        const url = "/customers";

        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(customerData)
        }).then(r => r.json().then(r => console.log("successful fetch to: /customers, method= POSTS -> \n \t " +
            "отправились данные по вновь-созданному customer:", r)));
    }


    return (
        <><h3>Создать нового Customer <span style={{fontSize: 10}}> (бери по дефолту!  )</span>:</h3>
            <form onSubmit={submit}>
                <label>name:
                    <input ref={inputName} name="name" type="text" defaultValue={"vlad"} placeholder="input customer name"/></label><br/>
                <label>email:
                    <input ref={inputEmail} name="email" type="text" defaultValue={"vlad@ukr.net"} placeholder="input customer email"/></label><br/>
                <label>age:
                    <input ref={inputAge} name="age" type="number" defaultValue="40" placeholder="input customer age"/></label><br/>
                submit <Button type="submit" color="primary" variant="contained">submit</Button>
            </form>
        </>
    );
};

export default CreateCustomer;