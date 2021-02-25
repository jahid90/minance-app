import { useParams } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';

interface Params {
    id: string;
}

const DepositDetails = () => {
    const { id }: Params = useParams();

    return (
        <Segment secondary className='deposit-details-container page-container'>
            Details for deposit #{id} here.
        </Segment>
    )
};

export default DepositDetails;
