import { Navigate } from 'react-router-dom';
import { useAppContext } from '../context/AppContextProvider';

interface AuthRouteParams {
    children: JSX.Element;
}

const AuthenticatedRoute = (params: AuthRouteParams) => {
    const { token } = useAppContext();

    if (!token) {
        // TODO: add current location to `from` so the login page can redirect to the intended page
        return <Navigate to='/login' replace state={{ from: '/' }} />;
    }

    return params.children;
};

export default AuthenticatedRoute;
