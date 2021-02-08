import { useParams } from 'react-router-dom';

interface Params {
    id: string;
}

const InvestmentDetail = () => {
    const { id }: Params = useParams();

    return <div>Investment detail for investment #{id} here.</div>;
};

export default InvestmentDetail;
