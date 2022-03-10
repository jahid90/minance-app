import { Link, Redirect } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';
import { useAppContext } from '../context/AppContextProvider';

const Home = () => {
    const { token } = useAppContext();
    if (token) {
        return <Redirect to='/information' />
    }

    return (
        <Segment secondary className='home-container page-container'>
            <div className='centered columnar h500'>
                <p>You need to be logged in to view this content.</p>
                <p>
                    <Link to='/login'>Login</Link> or <Link to='/register'>Register</Link>
                </p>
            </div>
        </Segment>
    );
};

export default Home;
