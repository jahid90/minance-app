import { Dispatch } from 'react';
import { login, logout, register } from '../services/auth-service';

export const Action = {
    LOG_USER_IN: 'log-user-in',
    LOG_USER_OUT: 'log-user-out',
    REGISTER_USER: 'register-user',
    USER_LOGGED_IN: 'user-logged-in',
    USER_LOGGED_OUT: 'user-logged-out',
    USER_REGISTERED: 'user-registered',
};

export interface IState {
    token?: string;
}

export interface IAction {
    type: string;
    data?: Record<string, unknown>;
}

export const reducer = (state: IState, action: IAction): IState => {
    switch (action.type) {
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
        try {
            console.info(`received: ${action.type}`);

            switch (action.type) {
                case Action.LOG_USER_IN:
                    const l_username = action.data?.username as string;
                    const l_password = action.data?.password as string;
                    const l_accessToken = await login(l_username, l_password);
                    dispatch({ type: Action.USER_LOGGED_IN, data: { token: l_accessToken } });
                    break;

                case Action.LOG_USER_OUT:
                    const l_token = action.data?.token as string;
                    await logout(l_token);
                    dispatch({ type: Action.USER_LOGGED_OUT });
                    break;

                case Action.REGISTER_USER:
                    const r_username = action.data?.username as string;
                    const r_email = action.data?.email as string;
                    const r_password = action.data?.password as string;
                    const r_confirmPassword = action.data?.password as string;
                    await register(r_username, r_email, r_password, r_confirmPassword);
                    dispatch({ type: Action.USER_REGISTERED });
                    break;

                default:
                    dispatch(action);
                    break;
            }
        } catch (err) {
            throw err;
        }
    };
};
