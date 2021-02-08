import { useParams } from 'react-router-dom';

interface Params {
    id: string;
}

const TransactionDetail = () => {
    const { id }: Params = useParams();

    return <div>Transaction detail for transaction #{id} here.</div>;
};

export default TransactionDetail;
