import decodeToken from 'jwt-decode';

export interface IToken {
    username: string;
    email: string;
    roles: Array<string>;
    iat: number;
    exp: number;
}

export const hasExpired = (token: string) => {
    const decoded = decodeToken(token) as IToken;

    // exp is saved as secs since epoch; Date.now gives millis since epoch
    return Date.now() > decoded.exp * 1000;
}

export const decode = (token: string): IToken => {
    return decodeToken(token);
}