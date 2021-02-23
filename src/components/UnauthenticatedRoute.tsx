import { Redirect, Route, RouteProps } from 'react-router-dom';
import { useAppContext } from '../context/AppContextProvider';

const UnauthenticatedRoute = (props: RouteProps) => {
    const { token } = useAppContext();

    if (token) {
        return <Redirect to={{ pathname: '/profile', state: props.location }} />;
    }

    return <Route {...props} />;
};

export default UnauthenticatedRoute;
