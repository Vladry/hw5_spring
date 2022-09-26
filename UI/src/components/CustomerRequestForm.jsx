import React from 'react';
import AllCustomers from "./AllCustomers";
import Button from "@material-ui/core/Button";

const CustomerRequestForm = (props) => {
    const {customers, getCustomers} = props;

    return (
        <div>
            <header className="App-header">
                <p>
                    All Customer List:
                </p>
                <div>
                    <AllCustomers customers={customers}/>
                </div>
            </header>
            <div>

                <Button type="button" className=""
                        color='primary'
                        variant='contained'
                        onClick={getCustomers}>
                    get customers
                </Button>
            </div>
        </div>
    );
};

export default CustomerRequestForm;