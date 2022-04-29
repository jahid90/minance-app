import { useParams } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';

const InvestmentDetail = () => {
    const { id } = useParams();

    return (
        <Segment secondary className='investment-details-container page-container'>
            Details for investment #{id} here.
        </Segment>
    );
};

export default InvestmentDetail;
