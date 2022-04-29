import { lazy, Suspense } from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';

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

const AppRoutes = () => {
    return (
        <Router basename='/minance'>
            <Nav />
            <Suspense fallback='Loading...'>
                <Routes>
                    <Route path='/' element={<Home />} />
                    <Route
                        path='/deposits'
                        element={
                            <AuthenticatedRoute>
                                <Deposits />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route
                        path='deposit/:id'
                        element={
                            <AuthenticatedRoute>
                                <DepositDetails />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route
                        path='/information'
                        element={
                            <AuthenticatedRoute>
                                <InformationGroup />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route
                        path='/information/:id'
                        element={
                            <AuthenticatedRoute>
                                <InformationDetail />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route
                        path='/investment/:id'
                        element={
                            <AuthenticatedRoute>
                                <InvestmentDetail />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route
                        path='/login'
                        element={
                            <UnauthenticatedRoute>
                                <Login />
                            </UnauthenticatedRoute>
                        }
                    />
                    <Route
                        path='/register'
                        element={
                            <UnauthenticatedRoute>
                                <Register />
                            </UnauthenticatedRoute>
                        }
                    />
                    <Route
                        path='/profile'
                        element={
                            <AuthenticatedRoute>
                                <Profile />
                            </AuthenticatedRoute>
                        }
                    />
                    <Route element={<PageNotFound />} />
                </Routes>
            </Suspense>
        </Router>
    );
};

export default AppRoutes;
