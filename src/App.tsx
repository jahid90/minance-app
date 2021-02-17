import { Container } from 'semantic-ui-react';

import './App.css';
import Nav from './components/Nav';
import Routes from './components/Routes';

const App = () => {
    return (
        <>
            <Nav />
            <div className='app'>
                <Container fluid>
                    <Routes />
                </Container>
            </div>
        </>
    );
};

export default App;
