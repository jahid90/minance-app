import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import AuthenticatedRoute from './AuthenticatedRoute';
import Home from '../pages/Home';
import InvestmentDetail from '../pages/InvestmentDetail';
import Login from '../pages/Login';
import Profile from '../pages/Profile';
import PageNotFound from '../pages/PageNotFound';
import TransactionDetail from '../pages/TransactionDetail';
import Nav from './Nav';
import Register from '../pages/Register';
import UnauthenticatedRoute from './UnauthenticatedRoute';

const Routes = () => {
    return (
        <Router>
            <Nav />
            <div className='app-content'>
                <Switch>
                    <AuthenticatedRoute exact path='/' component={Home} />
                    <AuthenticatedRoute exact path='/transaction/:id' component={TransactionDetail} />
                    <AuthenticatedRoute exact path='/investment/:id' component={InvestmentDetail} />
                    <UnauthenticatedRoute exact path='/login' component={Login} />
                    <UnauthenticatedRoute exact path='/register' component={Register} />
                    <AuthenticatedRoute exact path='/profile' component={Profile} />
                    <Route component={PageNotFound} />
                </Switch>
            </div>
        </Router>
    );
};

export default Routes;
