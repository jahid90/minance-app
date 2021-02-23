import { createContext, Dispatch, PropsWithChildren, ReactNode, useContext, useReducer } from 'react';

export interface IState {
    token?: string;
}

export interface IAction {
    type: string;
    data?: Record<string, unknown>;
}

export const Action = {
    USER_LOGGED_IN: 'user-logged-in',
    USER_LOGGED_OUT: 'user-logged-out',
};

const AppContext = createContext({} as IState);
const DispatchContext = createContext({} as Dispatch<IAction>);

export const useAppContext = () => {
    const context = useContext(AppContext);

    if (context === undefined) {
        throw new Error('AppContext must be used inside AppContextProvider');
    }

    return context;
};

export const useDispatchContext = () => {
    const context = useContext(DispatchContext);

    if (context === undefined) {
        throw new Error('DispatchContext must be used inside AppContextProvider');
    }

    return context;
};

const reducer = (state: IState, action: IAction): IState => {
    console.log(`received: ${action.type}`);

    switch (action.type) {
        case Action.USER_LOGGED_IN:
            return {
                ...state,
                token: action.data?.accessToken as string,
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

const AppContextProvider = (props: PropsWithChildren<ReactNode>) => {
    const [state, dispatch] = useReducer(reducer, {});

    return (
        <DispatchContext.Provider value={dispatch}>
            <AppContext.Provider value={state}>{props.children}</AppContext.Provider>
        </DispatchContext.Provider>
    );
};

export default AppContextProvider;
