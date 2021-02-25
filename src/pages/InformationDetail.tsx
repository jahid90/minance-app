import { useParams } from 'react-router-dom';

interface Params {
    id: string;
}

const InformationDetail = () => {
    const { id }: Params = useParams();

    return <div>Details for entry #{id} here.</div>;
};

export default InformationDetail;
