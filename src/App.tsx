import AppContainer from './components/AppContainer';
import AppContextProvider from './context/AppContextProvider';

import './App.css';

const App = () => {
    return (
        <AppContextProvider>
            <div className='app'>
                <AppContainer />
            </div>
        </AppContextProvider>
    );
};

export default App;
