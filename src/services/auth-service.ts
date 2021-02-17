import axios from 'axios';

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

export const login = async (username: string, password: string): Promise<string | ErrorResponse> => {
    const {
        data: { accessToken },
    }: ILoginResponse = await auth.post('/login', {
        username,
        password,
    });

    return accessToken;
};

export const refresh = async (): Promise<string | ErrorResponse> => {
    const {
        data: { accessToken },
    }: ILoginResponse = await auth.post('/renew');

    return accessToken;
};
