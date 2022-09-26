import React from 'react';
import Customer from "./Customer";

const AllCustomers = ({customers}) => {
    let allCustomers = [];

    if (customers) {
        allCustomers = customers.map(c =>
            <Customer key={c.id} props={c}/>
        );
    }

    return (
        <div>
            {allCustomers}
        </div>
    );
};

export default AllCustomers;