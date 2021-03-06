import React from 'react';
import moment from 'moment';
import { useNavigate } from 'react-router-dom';
import { Button, Card, Icon } from 'semantic-ui-react';

import Money from './Money';
import { IInformation } from '../models/Information';

interface Props {
    item: IInformation;
    type: string;
}

const InformationCard = ({ item, type }: Props) => {
    const navigate = useNavigate();

    return (
        <Card className='information-card'>
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
                    <Button basic color='green' onClick={() => navigate(`/information/${item.id}`)}>
                        Details
                    </Button>
                    <Button basic color='blue' onClick={() => navigate(`/investment/${item.id}`)}>
                        Investment
                    </Button>
                </div>
            </Card.Content>
        </Card>
    );
};

export default InformationCard;
