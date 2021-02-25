import axios from 'axios';

const backend = axios.create({
    baseURL: '/api/minance'
});

export interface IDepositResponse {
    value: string;
}

export const getAllDepositsForUser = async (user: string) => {
    return await backend.get(`/api/v1/deposits/term/filter?user=${user}`);
};
