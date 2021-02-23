import { FormEvent, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { Button, Form, Image, Label, Message, Segment } from 'semantic-ui-react';

import { useDispatchContext } from '../context/AppContextProvider';
import { Action } from '../context/app-reducer';
import { ErrorResponse } from '../services/auth-service';

import profileImage from '../assets/profile.png';

const Register = () => {
    const [error, setError] = useState({} as ErrorResponse);
    const [username, setUsername] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const dispatch = useDispatchContext();
    const history = useHistory();

    const handleSubmit = async (e: FormEvent<HTMLElement>) => {
        try {
            e.preventDefault();
            setError({} as ErrorResponse);

            await dispatch({ type: Action.REGISTER_USER, data: { username, email, password, confirmPassword } });

            setUsername('');
            setEmail('');
            setPassword('');
            setConfirmPassword('');

            history.push('/login');
        } catch (e) {
            console.error(e);
            setError(e.response?.data?.error);
        }
    };

    return (
        <Segment placeholder className='form-container'>
            <Form className='login-form form' onSubmit={handleSubmit}>
                <h1>Register</h1>
                <Image src={profileImage} className='profile-image' />
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
                        placeholder='Email'
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
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
                <Form.Field className='form-field-container'>
                    <Form.Input
                        placeholder='Confirm Password'
                        value={confirmPassword}
                        type='password'
                        onChange={(e) => setConfirmPassword(e.target.value)}
                        className='form-field'
                    />
                </Form.Field>
                <Button type='submit' floated='right' className='form-field'>
                    Submit
                </Button>
                <Message>
                    <div>
                        Already registered?{' '}
                        <Label basic color='blue' as='a' onClick={() => history.push('/login')}>
                            Login
                        </Label>
                    </div>
                </Message>
                {error?.message && (
                    <Message negative>
                        <Message.Header>{error.message}</Message.Header>
                        <Message.List>
                            {error.data?.username && <Message.Item>{error.data.username}</Message.Item>}
                            {error.data?.email && <Message.Item>{error.data.email}</Message.Item>}
                            {error.data?.password && <Message.Item>{error.data.password}</Message.Item>}
                            {error.data?.confirmPassword && <Message.Item>{error.data.confirmPassword}</Message.Item>}
                        </Message.List>
                    </Message>
                )}
            </Form>
        </Segment>
    );
};

export default Register;
