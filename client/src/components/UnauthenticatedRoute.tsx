import { Navigate } from 'react-router-dom';
import { useAppContext } from '../context/AppContextProvider';

interface UnauthRouteParams {
    children: JSX.Element;
}

const UnauthenticatedRoute = (params: UnauthRouteParams) => {
    const { token } = useAppContext();

    if (token) {
        // TODO: add current location to `from` so the login page can redirect to the intended page
        return <Navigate to='/profile' replace state={{ from: '/' }} />;
    }

    return params.children;
};

export default UnauthenticatedRoute;
