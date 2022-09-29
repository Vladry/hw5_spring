import './App.css';
import React, {useEffect, useState} from 'react';
import CustomerRequestForm from "../components/CustomerRequestForm";
import CreateCustomer from "../components/CreateCustomer";
import CreateAccount from "../components/CreateAccount";
import CreateSystemUser from "../components/CreateSystemUser";
import AppRouts from "./AppRouts";
import SockJS from "sockjs-client";
// import Stomp from "stomp-websocket";     - тоже хорошо работало, но начало выбивать коннекшн...
import Stomp from "stompjs";
import WebSocketBox from "../components/WebSocketBox";

let stompClient =null;

function App() {
    let customers = [];
    const [acc, setAcc] = useState({});

    function connect() {
        const socket = new SockJS("/ws");
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            stompClient.subscribe("/topic/accounts", accountSubscription/*, {"Content-Type": "application/json"}*/);

        });
    }

    function accountSubscription(accountUpdateMsg) {
        const msg = JSON.parse(accountUpdateMsg.body);
        setAcc(msg);
        console.log("accountUpdateMsg: ", msg);
    }


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

    useEffect(() => {
        connect();
        console.log("in useEffect-> connecting()")
    }, []);

    return (
        <div className="App">

            <CustomerRequestForm customers={customersArr} getCustomers={getCustomers}/>
            <br/><br/><br/>
            <CreateCustomer/><br/><br/>
            <CreateAccount/><br/><br/>
            <CreateSystemUser/>
            <WebSocketBox acc={acc}/>
            <AppRouts/>
        </div>
    );

}

export default App;
