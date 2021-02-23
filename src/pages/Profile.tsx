import decode from 'jwt-decode';
import { useHistory } from 'react-router-dom';
import { Button } from 'semantic-ui-react';

import { useAppContext } from '../context/AppContextProvider';
import { logout } from '../services/auth-service';

interface IToken {
    username?: string;
    email?: string;
    roles?: Array<string>;
}

const Profile = () => {
    const { token } = useAppContext();
    const history = useHistory();

    let decoded: IToken = {};
    if (token) {
        decoded = decode(token);
    }

    const handleClick = async () => {
        await logout(token as string);
        history.push('/login');
    };

    return (
        <>
            <p>Hi {decoded.username || 'stranger'}! This is the profile page.</p>

            <Button basic color='red' onClick={handleClick}>
                Logout
            </Button>
        </>
    );
};

export default Profile;
