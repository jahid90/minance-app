import { createContext } from "react";

export interface IAppContext {
    token: string;
    setToken: (t: string) => void;
}

export const AppContext = createContext({});
