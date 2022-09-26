import React,{useRef} from 'react';
import Button from '@material-ui/core/button';

const CreateAccount = () => {

    const inputId = useRef(null);
    const inputCurrency = useRef(null);
    const inputBalance = useRef(null);

    const submit = (event) => {
        event.preventDefault();


        const url = "/accounts";
         fetch(url, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({
                currency:  inputCurrency.current.value,
                balance :  inputBalance.current.value,
                customer_id:  inputId.current.value
            }) })
            .then(r=>r.json()).then(r=> console.log(r));
    }

    return (
        <form onSubmit={submit}>
            <label>customerId  <input ref={inputId} type="number" name="customer" placeholder="input customerId"/><br/></label>
            <label>currency  <input ref={inputCurrency} type="number" name="currency"
                                    placeholder="input currency number"/><br/></label>
            <label>balance  <input ref={inputBalance} type="number" name="balance"
                                  placeholder="input balance amount"/><br/></label>
            submit <Button type="submit" color="primary" variant="contained">submit</Button>
        </form>
    );
};

export default CreateAccount;