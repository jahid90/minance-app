import { Dispatch } from 'react';
import { login, logout } from '../services/auth-service';

export const Action = {
    USER_LOGGED_IN: 'user-logged-in',
    USER_LOGGED_OUT: 'user-logged-out',
    LOG_USER_IN: 'log-user-in',
    LOG_USER_OUT: 'log-user-out',
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

        default:
            return state;
    }
};

export const wrapDispatch = (dispatch: Dispatch<IAction>) => {
    return async (action: IAction) => {
        console.info(`received: ${action.type}`);

        switch (action.type) {
            case Action.LOG_USER_IN:
                const accessToken = await login(action.data?.username as string, action.data?.password as string);
                dispatch({ type: Action.USER_LOGGED_IN, data: { token: accessToken } });
                break;

            case Action.LOG_USER_OUT:
                await logout(action.data?.token as string);
                dispatch({ type: Action.USER_LOGGED_OUT });
                break;

            default:
                dispatch(action);
                break;
        }
    };
};
