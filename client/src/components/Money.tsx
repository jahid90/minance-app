import './Money.css';

interface Props {
    amount: {
        rupee: number;
        paise: number;
    };
}

function Money({ amount }: Props) {
    const format = (amount: number) =>
        amount.toLocaleString('en-IN', { useGrouping: true });

    return (
        <div className='money'>
            <span className='money-symbol'>&#8377;</span>
            <span className='money-rupee'>{format(amount.rupee)}</span>
            <span className='money-separator'>.</span>
            <span className='money-paise'>{amount.paise || '00'}</span>
        </div>
    );
}

export default Money;
