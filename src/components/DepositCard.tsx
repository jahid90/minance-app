import React from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Card, Icon } from 'semantic-ui-react';

import { IDeposit } from '../models/Deposit';

interface Props {
    item: IDeposit;
}

const DepositCard = ({ item }: Props) => {
    const history = useHistory();

    return (
        <Card className='information-card'>
            <Card.Content>
                <Icon name='file alternate' className='profile-image floated-right' size='big' />
                <Card.Header>Term Deposit</Card.Header>
                <Card.Meta>{item.id}</Card.Meta>
                <Card.Description>More details here.</Card.Description>
            </Card.Content>
            <Card.Content extra>
                <div className='ui two buttons'>
                    <Button basic color='blue' onClick={() => history.push(`/deposit/${item.id}`)}>
                        See Details
                    </Button>
                </div>
            </Card.Content>
        </Card>
    );
};

export default DepositCard;
