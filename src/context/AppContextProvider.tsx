import { createContext, Dispatch, PropsWithChildren, ReactNode, useContext, useReducer } from 'react';

import { IAction, IState, reducer, wrapDispatch } from './app-reducer';

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

const AppContextProvider = (props: PropsWithChildren<ReactNode>) => {
    const [state, dispatch] = useReducer(reducer, { token: '' });
    const dispatchMiddleware = wrapDispatch(dispatch);

    return (
        <DispatchContext.Provider value={dispatchMiddleware}>
            <AppContext.Provider value={state}>{props.children}</AppContext.Provider>
        </DispatchContext.Provider>
    );
};

export default AppContextProvider;
