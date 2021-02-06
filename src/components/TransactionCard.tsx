import React from 'react';
import { Card } from 'semantic-ui-react';
import moment from 'moment';

import Money from './Money';
import { ITransaction } from './Transaction';

interface Props {
    item: ITransaction;
    type: string;
}

function TransactionCard({ item, type }: Props) {
    return (
        <Card>
            <Card.Content>
                <Card.Header>{item.title}</Card.Header>
                <Card.Meta>
                    {type} {moment(item.date).fromNow()}
                </Card.Meta>
                <Card.Description>
                    <Money amount={item.amount} />
                </Card.Description>
            </Card.Content>
        </Card>
    );
}

export default TransactionCard;
