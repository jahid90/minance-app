import { Redirect, Route, RouteProps } from 'react-router-dom';
import { useAppContext } from '../context/AppContextProvider';

const AuthenticatedRoute = (props: RouteProps) => {
    const { token } = useAppContext();

    if (!token) {
        return <Redirect to={{ pathname: '/login', state: props.location }} />;
    }

    return <Route {...props} />;
};

export default AuthenticatedRoute;
