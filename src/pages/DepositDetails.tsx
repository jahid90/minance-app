// @ts-nocheck
import { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { List, Segment } from 'semantic-ui-react';

import { getOne } from '../services/backend-service';

interface Params {
    id: string;
}

const DepositDetails = () => {
    const { id }: Params = useParams();
    const [detail, setDetail] = useState({});

    useEffect(() => {
        (async () => {
            try {
                const result = await getOne(id);
                console.log(result);
                setDetail(result.data);

            } catch (e) {
                console.error(e);
            }
        })();
    }, []);

    return (
        <Segment secondary className='deposit-details-container page-container'>
            Details for deposit #{id} here.

            {detail.depositor && <List>
                <List.Item>By: {detail.depositor}</List.Item>
                <List.Item>On: {detail.createdOn.year}/{detail.createdOn.month}/{detail.createdOn.day}</List.Item>
                <List.Item>Of: {detail.amount.currency} {detail.amount.fixed}.{detail.amount.decimal}</List.Item>
                <List.Item>For {detail.period.years} years, {detail.period.months} months and {detail.period.days} days</List.Item>
                <List.Item>At: {detail.interestRate.fixed}.{detail.interestRate.decimal}% {detail.interestRate.type} interest</List.Item>
                <List.Item>Maturity: {detail.maturityInstruction}</List.Item>
            </List>
            }
        </Segment>
    )
};

export default DepositDetails;
