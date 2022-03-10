import { Dispatch } from 'react';

import { login, logout, register } from '../services/auth-service';
import { hasExpired } from '../services/token';

export const Action = {
    APP_LOADED: 'app-loaded',
    LOAD_APP: 'load-app',
    LOG_USER_IN: 'log-user-in',
    LOG_USER_OUT: 'log-user-out',
    REGISTER_USER: 'register-user',
    USER_LOGGED_IN: 'user-logged-in',
    USER_LOGGED_OUT: 'user-logged-out',
    USER_REGISTERED: 'user-registered',
};

export interface IState {
    token: string;
}

export interface IAction {
    type: string;
    data?: Record<string, string>;
}

export const reducer = (state: IState, action: IAction): IState => {
    switch (action.type) {
        case Action.APP_LOADED:
            return {
                ...state,
                token: action.data?.token as string,
            };

        case Action.USER_LOGGED_IN:
            return {
                ...state,
                token: action.data?.token as string,
            };

        case Action.USER_LOGGED_OUT:
            return {
                ...state,
                token: '',
            };

        case Action.USER_REGISTERED:
            return {
                ...state,
            };

        default:
            return {
                ...state,
            };
    }
};

export const wrapDispatch = (dispatch: Dispatch<IAction>) => {
    return async (action: IAction) => {
        console.info(`received: ${action.type}`);

        // TODO - consider removing all locals to not need the curly braces hack; just use well-named functions?
        switch (action.type) {
            case Action.LOAD_APP: {
                const token = (await localStorage.getItem('token'));

                if (!token) {
                    console.log('no saved session was found')
                } else if (!hasExpired(token)) {
                    dispatch({ type: Action.APP_LOADED, data: { token } });
                } else {
                    await localStorage.removeItem('token');
                    console.info('token has expired');
                }
                break;
            }

            case Action.LOG_USER_IN: {
                if (action.data) {
                    const { username, password } = action.data;
                    const token = (await login(username, password)) as string;
                    await localStorage.setItem('token', token);
                    dispatch({ type: Action.USER_LOGGED_IN, data: { token } });
                }
                break;
            }

            case Action.LOG_USER_OUT: {
                const token = action.data?.token as string;
                await logout(token);
                await localStorage.removeItem('token');
                dispatch({ type: Action.USER_LOGGED_OUT });
                break;
            }

            case Action.REGISTER_USER: {
                if (action.data) {
                    const { username, email, password, confirmPassword } = action.data;
                    await register(username, email, password, confirmPassword);
                    dispatch({ type: Action.USER_REGISTERED });
                }
                break;
            }

            default: {
                dispatch(action);
                break;
            }
        }
    };
};
