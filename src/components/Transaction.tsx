export interface ITransaction {
    id: number;
    title: string;
    date: Date;
    amount: {
        rupee: number;
        paise: number;
    };
}

const Transaction = {};

export default Transaction;
