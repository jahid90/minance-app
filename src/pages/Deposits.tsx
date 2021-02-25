import React, { useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Label, List, Segment } from 'semantic-ui-react';
import { useAppContext } from '../context/AppContextProvider';

import { getAllDepositsForUser } from '../services/backend-service';
import { decode } from '../services/token';

const Deposits = () => {
    const [deposits, setDeposits] = useState([]);
    const { token } = useAppContext();
    const history = useHistory();
    const decoded = decode(token);

    useEffect(() => {
        (async () => {
            try {
                const result = await getAllDepositsForUser(decoded.username);
                setDeposits(result.data);
            } catch (e) {
                console.error(e);
            }
        })();
    });

    return (
        <Segment secondary className='deposits-container page-container'>
            <Label>Here's the list of all your deposits</Label>
            <List>
            {deposits.map((deposit) => (
                <List.Item>
                    <Label basic color='blue' as='a' onClick={() => history.push(`/deposit/${deposit}`)}>
                        See details of ${deposit}
                    </Label>
                </List.Item>
            ))}
            </List>
        </Segment>
    );
};

export default Deposits;
