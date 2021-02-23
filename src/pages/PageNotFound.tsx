import { Link } from 'react-router-dom';
import { Segment } from 'semantic-ui-react';

import './PageNotFound.css';

function PageNotFound() {
    return (
        <Segment secondary className='not-found'>
            <div>The requested page could not be found.</div>
            <div>Go back to <Link to='/' className='inline-link'>home</Link>.</div>
        </Segment>
    );
}

export default PageNotFound;
