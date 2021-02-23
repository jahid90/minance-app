import { FormEvent, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Form, Image, Message } from 'semantic-ui-react';

import { Action, useDispatchContext } from '../context/AppContextProvider';
import { ErrorResponse, login } from '../services/auth-service';

import profileImage from '../assets/profile.png';

const Login = () => {
    const [error, setError] = useState({} as ErrorResponse);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatchContext();
    const history = useHistory();

    const handleSubmit = async (e: FormEvent<HTMLElement>) => {
        try {
            e.preventDefault();
            setError({} as ErrorResponse);

            const accessToken = await login(username, password);
            dispatch({ type: Action.USER_LOGGED_IN, data: { accessToken } });

            setUsername('');
            setPassword('');

            history.push('/');
        } catch (e) {
            console.error(e);
            setError(e.response?.data?.error);
        }
    };

    return (
        <Form className='login-form form' onSubmit={handleSubmit}>
            <Image src={profileImage} className='profile-image' />
            <Form.Field>
                <Form.Input
                    placeholder='Username'
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    autoFocus
                />
            </Form.Field>
            <Form.Field>
                <Form.Input
                    placeholder='Password'
                    value={password}
                    type='password'
                    onChange={(e) => setPassword(e.target.value)}
                />
            </Form.Field>
            <Button type='submit' floated='right'>
                Submit
            </Button>
            {error?.message && (
                <Message error header={error.message} list={[error.data.username, error.data.password]} />
            )}
        </Form>
    );
};

export default Login;
