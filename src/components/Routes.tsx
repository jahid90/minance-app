import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import Home from '../pages/Home';
import InvestmentDetail from '../pages/InvestmentDetail';
import Login from '../pages/Login';
import Profile from '../pages/Profile';
import PageNotFound from '../pages/PageNotFound';
import TransactionDetail from '../pages/TransactionDetail';
import Nav from './Nav';

const Routes = () => {
    return (
        <Router>
            <Nav />
            <div className='app-content'>
                <Switch>
                    <Route exact path='/' component={Home} />
                    <Route exact path='/transaction/:id' component={TransactionDetail} />
                    <Route exact path='/investment/:id' component={InvestmentDetail} />
                    <Route exact path='/login' component={Login} />
                    <Route exact path='/profile' component={Profile} />
                    <Route component={PageNotFound} />
                </Switch>
            </div>
        </Router>
    );
};

export default Routes;
