import React from 'react';
import { Card } from 'semantic-ui-react';

import { ITransaction } from '../components/Transaction';
import TransactionCard from '../components/TransactionCard';

interface Props {
    data: Array<ITransaction>;
}

function Home({ data }: Props) {
    return (
        <Card.Group>
            {data
                .sort((a, b) => a.date.getTime() - b.date.getTime())
                .map((item) => (
                    <TransactionCard
                        key={item.id}
                        item={item}
                        type={
                            item.id === 1
                                ? 'Expiring'
                                : item.id === 2
                                ? 'Vesting'
                                : 'Renewal'
                        }
                    />
                ))}
        </Card.Group>
    );
}

export default Home;
