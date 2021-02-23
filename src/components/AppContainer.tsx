import React, { useEffect, useState } from 'react';
import { Container, Dimmer, Loader } from 'semantic-ui-react';

import { Action } from '../context/app-reducer';
import { useDispatchContext } from '../context/AppContextProvider';
import Routes from './Routes';

function AppContainer() {
    const [loading, setLoading] = useState(true);
    const dispatch = useDispatchContext();
    useEffect(() => {
        (async () => {
            try {
                await dispatch({ type: Action.LOAD_APP });
                setLoading(false);
            } catch (e) {
                // Ignore it. Failed to load previous session status. User will have to login again.
                console.warn(e);
            }
        })();
    }, [dispatch]);

    return (
        <Container fluid>
            {loading ? (
                <Dimmer active inverted>
                    <Loader />
                </Dimmer>
            ) : (
                <Routes />
            )}
        </Container>
    );
}

export default AppContainer;
