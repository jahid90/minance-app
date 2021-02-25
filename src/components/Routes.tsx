import { lazy, Suspense } from 'react';
import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import AuthenticatedRoute from './AuthenticatedRoute';
import Home from '../pages/Home';
import Nav from './Nav';
import UnauthenticatedRoute from './UnauthenticatedRoute';

const DepositDetails = lazy(() => import('../pages/DepositDetails'));
const Deposits = lazy(() => import('../pages/Deposits'));
const InvestmentDetail = lazy(() => import('../pages/InvestmentDetail'));
const InformationGroup = lazy(() => import('../pages/InformationGroup'));
const Login = lazy(() => import('../pages/Login'));
const PageNotFound = lazy(() => import('../pages/PageNotFound'));
const Profile = lazy(() => import('../pages/Profile'));
const Register = lazy(() => import('../pages/Register'));
const InformationDetail = lazy(() => import('../pages/InformationDetail'));

const Routes = () => {
    return (
        <Router>
            <Nav />
            <Suspense fallback='Loading...'>
                <Switch>
                    <Route exact path='/' component={Home} />
                    <AuthenticatedRoute exact path='/deposits' component={Deposits} />
                    <AuthenticatedRoute exact path='/deposit/:id' component={DepositDetails} />
                    <AuthenticatedRoute exact path='/information' component={InformationGroup} />
                    <AuthenticatedRoute exact path='/information/:id' component={InformationDetail} />
                    <AuthenticatedRoute exact path='/investment/:id' component={InvestmentDetail} />
                    <UnauthenticatedRoute exact path='/login' component={Login} />
                    <UnauthenticatedRoute exact path='/register' component={Register} />
                    <AuthenticatedRoute exact path='/profile' component={Profile} />
                    <Route component={PageNotFound} />
                </Switch>
            </Suspense>
        </Router>
    );
};

export default Routes;
