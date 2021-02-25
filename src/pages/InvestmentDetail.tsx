import { useParams } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';

interface Params {
    id: string;
}

const InvestmentDetail = () => {
    const { id }: Params = useParams();

    return (
        <Segment secondary className='investment-details-container page-container'>
            Details for investment #{id} here.
        </Segment>
    )
};

export default InvestmentDetail;
