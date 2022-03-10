import ReactDOM from 'react-dom';
import { act } from 'react-dom/test-utils';
import App from './App';

let container : HTMLElement | null;

beforeEach(() => {
    container = document.createElement('div');
    document.body.appendChild(container);
});

afterEach(() => {
    document.body.removeChild(container as HTMLElement);
    container = null;
});

test('renders app name', () => {
    act(() => {
        ReactDOM.render(<App />, container);
    });
    // const app = container.querySelector('app');
    // expect(app.text).toBe('Minance');
});
