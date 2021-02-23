import decode from 'jwt-decode';
import { useHistory } from 'react-router-dom';
import { Button, Segment } from 'semantic-ui-react';

import { Action } from '../context/app-reducer';
import { useAppContext, useDispatchContext } from '../context/AppContextProvider';

interface IToken {
    username?: string;
    email?: string;
    roles?: Array<string>;
}

const Profile = () => {
    const { token } = useAppContext();
    const dispatch = useDispatchContext();
    const history = useHistory();

    let decoded: IToken = {};
    if (token) {
        decoded = decode(token);
    }

    const handleClick = async () => {
        await dispatch({ type: Action.LOG_USER_OUT, data: { token } });

        history.push('/login');
    };

    return (
        <Segment className='profile-content'>
            <p>Hi {decoded.username || 'stranger'}! This is the profile page.</p>

            <div>
                <Button basic color='red' onClick={handleClick}>
                    Logout
                </Button>
            </div>
        </Segment>
    );
};

export default Profile;
