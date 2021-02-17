import { Route, BrowserRouter as Router, Switch } from 'react-router-dom';

import Home from '../pages/Home';
import InvestmentDetail from '../pages/InvestmentDetail';
import Login from '../pages/Login';
import PageNotFound from '../pages/PageNotFound';
import TransactionDetail from '../pages/TransactionDetail';

const Routes = () => {
    return (
        <Router>
            <Switch>
                <Route exact path='/' component={Home} />
                <Route exact path='/transaction/:id' component={TransactionDetail} />
                <Route exact path='/investment/:id' component={InvestmentDetail} />
                <Route exact path='/login' component={Login} />
                <Route component={PageNotFound} />
            </Switch>
        </Router>
    );
};

export default Routes;
