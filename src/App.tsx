import { Container } from 'semantic-ui-react';

import './App.css';
import Routes from './components/Routes';
import AppContextProvider from './context/AppContextProvider';

const App = () => {
    return (
        <AppContextProvider>
            <div className='app'>
                <Container fluid>
                    <Routes />
                </Container>
            </div>
        </AppContextProvider>
    );
};

export default App;
