import { lazy, Suspense } from 'react';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import AuthenticatedRoute from './AuthenticatedRoute';
import Home from '../pages/Home';
import Nav from './Nav';
import UnauthenticatedRoute from './UnauthenticatedRoute';

const InvestmentDetail = lazy(() => import('../pages/InvestmentDetail'));
const Login = lazy(() => import('../pages/Login'));
const PageNotFound = lazy(() => import('../pages/PageNotFound'));
const Profile = lazy(() => import('../pages/Profile'));
const Register = lazy(() => import('../pages/Register'));
const TransactionDetail = lazy(() => import('../pages/TransactionDetail'));

const Routes = () => {
    return (
        <Router>
            <Nav />
            <div className='app-content'>
                <Suspense fallback='Loading...'>
                    <Switch>
                        <Route exact path='/' component={Home} />
                        <AuthenticatedRoute exact path='/transaction/:id' component={TransactionDetail} />
                        <AuthenticatedRoute exact path='/investment/:id' component={InvestmentDetail} />
                        <UnauthenticatedRoute exact path='/login' component={Login} />
                        <UnauthenticatedRoute exact path='/register' component={Register} />
                        <AuthenticatedRoute exact path='/profile' component={Profile} />
                        <Route component={PageNotFound} />
                    </Switch>
                </Suspense>
            </div>
        </Router>
    );
};

export default Routes;
