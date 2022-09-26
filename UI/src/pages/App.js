import './App.css';
import React, {useState} from 'react';
import CustomerRequestForm from "../components/CustomerRequestForm";
import CreateCustomer from "../components/CreateCustomer";
import CreateAccount from "../components/CreateAccount";
import CreateSystemUser from "../components/CreateSystemUser";
import AppRouts from "./AppRouts";

function App() {
    let customers = [];

    const getCustomers = async () => {
        const allCustomersUrl = '/customers/all';


        try {
            await fetch(allCustomersUrl, {
                method: 'GET',
                headers: {'Content-Type': 'application/json'}
            }).then(r => r.json()).then(result => {
                setCustomersArr(result);
                customers = result;
                console.log("customers fetched: ", customers);
            });
        } catch {
            console.warn('error loading customers')
        }
    }
    const [customersArr, setCustomersArr] = useState([]);

    return (
        <div className="App">

            <CustomerRequestForm customers={customersArr} getCustomers={getCustomers}/>
            <br/><br/><br/>
            <CreateCustomer/><br/><br/>
            <CreateAccount/><br/><br/>
            <CreateSystemUser/>
            <AppRouts/>
        </div>
    );

}

export default App;
