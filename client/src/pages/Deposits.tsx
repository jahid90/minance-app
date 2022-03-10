import React, { useEffect, useState } from 'react';
import { Card, Label, Segment } from 'semantic-ui-react';

import DepositCard from '../components/DepositCard';
import { useAppContext } from '../context/AppContextProvider';
import { getAllForUser } from '../services/backend-service';
import { decode } from '../services/token';

const Deposits = () => {
    const [deposits, setDeposits] = useState([]);
    const { token } = useAppContext();
    const decoded = decode(token);

    useEffect(() => {
        (async () => {
            try {
                const result = await getAllForUser(decoded.username);
                setDeposits(result.data);
            } catch (e) {
                console.error(e);
            }
        })();
    });

    return (
        <Segment secondary className='deposits-container page-container'>
            {deposits.length === 0 ? (
                <Label basic color='yellow' className='deposit-header'>
                    No deposits were found.
                </Label>
            ) : (
                <Card.Group className='information-cards'>
                    {deposits.map((id) => (
                        <DepositCard key={id} item={{ id }} />
                    ))}
                    <Card className='information-card dummy hidden' />
                    <Card className='information-card dummy hidden' />
                </Card.Group>
            )}
        </Segment>
    );
};

export default Deposits;
