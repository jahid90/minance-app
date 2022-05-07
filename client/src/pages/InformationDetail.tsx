import { useParams } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';

const InformationDetail = () => {
    const { id } = useParams();

    return (
        <Segment secondary className='information-details-container page-container'>
            Details for entry #{id} here.
        </Segment>
    );
};

export default InformationDetail;
