import { createContext, Dispatch, PropsWithChildren, ReactNode, useContext, useMemo, useReducer } from 'react';

import { IAction, IState, reducer, wrapDispatch } from './app-reducer';

const AppContext = createContext<IState | undefined>(undefined);
const DispatchContext = createContext<Dispatch<IAction> | undefined>(undefined);

export const useAppContext = () => {
    const context = useContext(AppContext);

    if (!context) {
        throw new Error('AppContext must be used inside AppContextProvider');
    }

    return context;
};

export const useDispatchContext = () => {
    const context = useContext(DispatchContext);

    if (!context) {
        throw new Error('DispatchContext must be used inside AppContextProvider');
    }

    return context;
};

const AppContextProvider = (props: PropsWithChildren<ReactNode>) => {
    const [state, dispatch] = useReducer(reducer, { token: '' });
    const wrappedDispatch = useMemo(() => wrapDispatch(dispatch), []);
    const wrappedState = useMemo(() => state, [state]);

    return (
        <DispatchContext.Provider value={wrappedDispatch}>
            <AppContext.Provider value={wrappedState}>{props.children}</AppContext.Provider>
        </DispatchContext.Provider>
    );
};

export default AppContextProvider;
