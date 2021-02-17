import { useState } from 'react';
import { Container } from 'semantic-ui-react';

import './App.css';
import Nav from './components/Nav';
import Routes from './components/Routes';
import { AppContext } from './context/app-context';

const App = () => {
    const [token, setToken] = useState('');

    return (
        <AppContext.Provider value={{ token, setToken }}>
            <Nav />
            <div className='app'>
                <Container fluid>
                    <Routes />
                </Container>
            </div>
        </AppContext.Provider>
    );
};

export default App;
