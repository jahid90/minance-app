import { lazy } from 'react';
import { Card, Segment } from 'semantic-ui-react';

const InformationCard = lazy(() => import('../components/InformationCard'));

const data = [
    {
        id: 1,
        title: 'Fixed Deposit #1',
        date: new Date(2021, 3, 6),
        amount: {
            rupee: 98765432,
            paise: 10,
        },
    },
    {
        id: 2,
        title: 'Fixed Deposit #2',
        date: new Date(2022, 3, 8),
        amount: {
            rupee: 7638,
            paise: 50,
        },
    },
    {
        id: 3,
        title: 'Fixed Deposit #3',
        date: new Date(2021, 6, 21),
        amount: {
            rupee: 500000,
            paise: 0,
        },
    },
    {
        id: 4,
        title: 'Fixed Deposit #4',
        date: new Date(2024, 5, 15),
        amount: {
            rupee: 100000,
            paise: 0,
        },
    },
];

const InformationGroup = () => {
    return (
        <Segment secondary className='information-group-container page-container'>
            <Card.Group className='information-cards'>
                {data
                    .sort((a, b) => a.date.getTime() - b.date.getTime())
                    .map((item) => (
                        <InformationCard
                            key={item.id}
                            item={item}
                            type={item.id === 1 ? 'Expiring' : item.id === 2 ? 'Vesting' : 'Renewal'}
                        />
                    ))}
                <Card className='information-card dummy hidden' />
                <Card className='information-card dummy hidden' />
            </Card.Group>
        </Segment>
    );
};

export default InformationGroup;
