import { useContext, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Form, Label, Segment } from 'semantic-ui-react';

import { AppContext, IAppContext } from '../context/app-context';
import { ErrorResponse, login } from '../services/auth-service';

const Login = () => {
    const [error, setError] = useState({} as ErrorResponse);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { setToken } = useContext(AppContext) as IAppContext;
    const history = useHistory();

    const handleSubmit = async () => {
        try {
            setError({} as ErrorResponse);
            const accessToken = await login(username, password);
            setToken(accessToken as string);

            setUsername('');
            setPassword('');

            history.push('/');
        } catch (e) {
            console.error(e);
            setError(e.response?.data?.error);
        }
    };

    return (
        <>
            <Form className='login-form form' onSubmit={(e) => e.preventDefault()}>
                <Form.Field>
                    <label>Username</label>
                    <input placeholder='username..' value={username} onChange={(e) => setUsername(e.target.value)} />
                </Form.Field>
                <Form.Field>
                    <label>Password</label>
                    <input
                        placeholder='password..'
                        value={password}
                        type='password'
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </Form.Field>
                <Button type='submit' floated='right' onClick={handleSubmit}>
                    Submit
                </Button>
                {error?.message && (
                    <Segment className='errors'>
                        <Label basic color='red'>
                            {error.message}
                        </Label>
                        {error.data?.username?.forEach((message) => {
                            <Label basic color='red'>
                                {message}
                            </Label>;
                        })}
                        {error.data?.password?.forEach((message) => {
                            <Label basic color='red'>
                                {message}
                            </Label>;
                        })}
                    </Segment>
                )}
            </Form>
        </>
    );
};

export default Login;
