import axios from 'axios';

const backend = axios.create({
    baseURL: '/api/minance'
});

export interface IDepositResponse {
    value: string;
}

export const getAllForUser = async (user: string) => {
    return await backend.get(`/api/v1/deposits/term/filter?user=${user}`);
};

export const getOne = async (id: string) => {
    return await backend(`/api/v1/deposits/term/details/${id}`);
}
