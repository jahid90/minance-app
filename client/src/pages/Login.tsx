import { FormEvent, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Form, Icon, Label, Message, Segment } from 'semantic-ui-react';

import { useDispatchContext } from '../context/AppContextProvider';
import { Action } from '../context/app-reducer';
import { ErrorResponse } from '../services/auth-service';

interface ILoginProps {
    location?: {
        state?: {
            from?: string;
        };
    };
}

const Login = (props: ILoginProps) => {
    const [error, setError] = useState({} as ErrorResponse);
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const dispatch = useDispatchContext();
    const navigate = useNavigate();

    const handleSubmit = async (e: FormEvent<HTMLElement>) => {
        try {
            e.preventDefault();
            setError({} as ErrorResponse);

            dispatch({ type: Action.LOG_USER_IN, data: { username, password } });

            setUsername('');
            setPassword('');

            navigate(props?.location?.state?.from || '/');
        } catch (err: any) {
            console.error(err);
            setError(err.response?.data?.error);
        }
    };

    return (
        <Segment secondary className='form-container page-container w600'>
            <Form className='login-form form centered columnar' onSubmit={handleSubmit}>
                <h1>Login</h1>
                <Icon name='user' className='profile-image' size='massive' />
                <Form.Field className='form-field-container'>
                    <Form.Input
                        placeholder='Username'
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        autoFocus
                        className='form-field'
                    />
                </Form.Field>
                <Form.Field className='form-field-container'>
                    <Form.Input
                        placeholder='Password'
                        value={password}
                        type='password'
                        onChange={(e) => setPassword(e.target.value)}
                        className='form-field'
                    />
                </Form.Field>
                <Button type='submit' className='form-field' color='blue'>
                    Submit
                </Button>
                <Message>
                    <div>
                        Not registered?{' '}
                        <Label basic color='blue' as='a' onClick={() => navigate('/register')}>
                            Register
                        </Label>
                    </div>
                </Message>
                {error?.message && (
                    <Message negative>
                        <Message.Header>{error.message}</Message.Header>
                        <Message.List>
                            {error.data?.username && <Message.Item>{error.data.username}</Message.Item>}
                            {error.data?.password && <Message.Item>{error.data.password}</Message.Item>}
                        </Message.List>
                    </Message>
                )}
            </Form>
        </Segment>
    );
};

export default Login;
