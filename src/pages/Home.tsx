import { lazy } from 'react';
import { Link } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';
import { useAppContext } from '../context/AppContextProvider';

const Transactions = lazy(() => import('../components/Transactions'));

const Home = () => {
    const { token } = useAppContext();

    return (
        <>
            {token && <Transactions />}
            {!token && (
                <Segment secondary className='home-content'>
                    <p>You need to be logged in to view this content.</p>
                    <p>
                        <Link to='/login'>Login</Link> or <Link to='/register'>Register</Link>
                    </p>
                </Segment>
            )}
        </>
    );
};

export default Home;
