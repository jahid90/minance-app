import React from 'react';
import moment from 'moment';
import { useHistory } from 'react-router-dom';
import { Button, Card, Icon } from 'semantic-ui-react';

import Money from './Money';
import { ITransaction } from '../models/Transaction';

interface Props {
    item: ITransaction;
    type: string;
}

const TransactionCard = ({ item, type }: Props) => {
    const history = useHistory();

    return (
        <Card className='transaction-card'>
            <Card.Content>
                <Icon name='file alternate' className='profile-image floated-right' size='big' />
                <Card.Header>{item.title}</Card.Header>
                <Card.Meta>
                    {type} {moment(item.date).fromNow()}
                </Card.Meta>
                <Card.Description>
                    <Money amount={item.amount} />
                </Card.Description>
            </Card.Content>
            <Card.Content extra>
                <div className='ui two buttons'>
                    <Button basic color='green' onClick={() => history.push(`/transaction/${item.id}`)}>
                        Details
                    </Button>
                    <Button basic color='blue' onClick={() => history.push(`/investment/${item.id}`)}>
                        Investment
                    </Button>
                </div>
            </Card.Content>
        </Card>
    );
};

export default TransactionCard;
