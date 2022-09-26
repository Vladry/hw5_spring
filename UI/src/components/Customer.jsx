import React from 'react';

const Customer = ({props}) => {
    const {id, name, email, age, accounts} = props;
    return (
        <div>
            <h4>Customer: {name}</h4>
            <p>id: {id} &nbsp;&nbsp; email: {email} &nbsp;&nbsp;  age: {age}</p>
            <p>accounts: </p>
        </div>
    );
};

export default Customer;