import React, {useRef} from 'react';
import Button from '@material-ui/core/button';

const CreateAccount = () => {

    const inputCustomerId = useRef(null);
    const inputCurrency = useRef(null);
    const inputBalance = useRef(null);

    const submit = (event) => {
        event.preventDefault();

        const accData = {
            currency: inputCurrency.current.value,
            balance: inputBalance.current.value,
            customerId: inputCustomerId.current.value
        }

        const url = "/accounts";
        fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(accData)
        })
            .then(r => r.json()).then(r =>{} /*console.log("success fetching to: /accounts, method= POSTS -> \n", r)*/);
    }

    return (
        <>
            <h3>Создать новый Account <span style={{fontSize: 10}}> (бери по дефолту!  )</span>:</h3>
            <form onSubmit={submit}>
                <label>customerId: <input ref={inputCustomerId} type="number" defaultValue={"2"} name="customer"
                                          placeholder="input customerId"/><br/></label>
                <label>номер валюты: <input ref={inputCurrency} type="number" defaultValue={"1"} name="currency"
                                            placeholder="input currency number"/><br/></label>
                <label>balance: <input ref={inputBalance} type="number" defaultValue={"50"} name="balance"
                                       placeholder="input balance amount"/><br/></label>
                submit <Button type="submit" color="primary" variant="contained">submit</Button>
            </form>
        </>
    );
};

export default CreateAccount;