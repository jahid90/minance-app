import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import { Container } from 'semantic-ui-react';

import './App.css';
import Nav from './components/Nav';
import Home from './pages/Home';
import PageNotFound from './pages/PageNotFound';

const data = [
    {
        id: 1,
        title: 'Fixed Deposit #1',
        date: new Date(2021, 3, 6),
        amount: {
            rupee: 98765432,
            paise: 10,
        },
    },
    {
        id: 2,
        title: 'Fixed Deposit #2',
        date: new Date(2022, 3, 8),
        amount: {
            rupee: 7638,
            paise: 50,
        },
    },
    {
        id: 3,
        title: 'Fixed Deposit #3',
        date: new Date(2021, 6, 21),
        amount: {
            rupee: 500000,
            paise: 0,
        },
    },
    {
        id: 4,
        title: 'Fixed Deposit #4',
        date: new Date(2024, 5, 15),
        amount: {
            rupee: 100000,
            paise: 0,
        },
    },
];

function App() {
    return (
        <>
            <Nav />
            <div className='app'>
                <Container fluid>
                    <Router>
                        <Switch>
                            <Route
                                exact
                                path='/'
                                component={() => <Home data={data} />}
                            />
                            <Route component={PageNotFound} />
                        </Switch>
                    </Router>
                </Container>
            </div>
        </>
    );
}

export default App;
