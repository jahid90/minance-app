import axios from 'axios';
import { useAppContext } from '../context/AppContextProvider';

const auth = axios.create({
    baseURL: 'http://auth.jahiduls.local',
    withCredentials: true,
});

interface ILoginResponse {
    data: {
        accessToken: string;
    };
}

export interface ErrorResponse {
    code: number;
    status: string;
    message: string;
    data: Record<string, Array<string>>;
}

export const register = async (
    username: string,
    email: string,
    password: string,
    confirmPassword: string
): Promise<void> => {
    await auth.post('/register', {
        username,
        email,
        password,
        confirmPassword,
    });
};

export const login = async (username: string, password: string): Promise<string | ErrorResponse> => {
    const {
        data: { accessToken },
    }: ILoginResponse = await auth.post('/login', {
        username,
        password,
    });

    return accessToken;
};

export const logout = async (token: string): Promise<void> => {
    await auth.delete('/logout', {
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });
};

export const refresh = async (): Promise<string | ErrorResponse> => {
    const {
        data: { accessToken },
    }: ILoginResponse = await auth.post('/renew');

    return accessToken;
};
