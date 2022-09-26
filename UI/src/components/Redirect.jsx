import React, {useEffect} from 'react';

const Redirect = ({url}) => {

    useEffect(()=>{
        window.location.href = url;
    },[url]);

    return (
            <h5>redirecting to {url}</h5>
    );
};
export default Redirect;