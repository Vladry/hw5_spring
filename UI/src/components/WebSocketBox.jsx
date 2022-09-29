import React from 'react';
import {Box} from "@material-ui/core";

const WebSocketBox = ({acc}) => {
    if(!acc.number){return null;}

    return (
        <Box sx={{m: '0 auto', p: 2, border: '2px solid red', borderRadius: 8, width: '400px'}}>
            <p>accountNumber: <span style={{fontSize: 12, fontWeight: 600}}>{`${acc.number.substr(0, 3)}***${acc.number.substr(-3, 3)}`}</span><br/>
                belonging to owner: <span style={{fontSize: 12, fontWeight: 600}}>{acc.customerName}</span><br/>
                has been changed or created:<br/>
                balance: <span style={{fontSize: 12, fontWeight: 600}}>{acc.balance} {acc.currency}</span>
            </p>
        </Box>
    );
};

export default WebSocketBox;