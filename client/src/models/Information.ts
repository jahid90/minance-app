export interface IInformation {
    id: number;
    title: string;
    date: Date;
    amount: {
        rupee: number;
        paise: number;
    };
}
