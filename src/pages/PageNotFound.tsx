import React from 'react';
import { Link } from 'react-router-dom';

import './PageNotFound.css';

function PageNotFound() {
    return (
        <div className='not-found'>
            The requested page could not be found.
            <br />
            Go back to <Link to='/'>home</Link>.
        </div>
    );
}

export default PageNotFound;
