import { useContext, useState } from 'react';
import { Menu } from 'semantic-ui-react';

import { AppContext, IAppContext } from '../context/app-context';

const Nav = () => {
    const [activeItem, setActiveItem] = useState('home');

    const { token: loggedIn } = useContext(AppContext) as IAppContext;

    return (
        <Menu size='huge' secondary>
            <Menu.Item header href='/'>
                Transact
            </Menu.Item>
            <Menu.Item
                name='past events'
                href='/past-events'
                active={activeItem === 'past-events'}
                onClick={() => setActiveItem('past-events')}
            />
            <Menu.Menu position='right'>
                <Menu.Item
                    name='about'
                    href='/about'
                    active={activeItem === 'about'}
                    onClick={() => setActiveItem('about')}
                />
            </Menu.Menu>
            {loggedIn ? (
                <Menu.Item
                    name='logout'
                    href='/logout'
                    active={activeItem === 'logout'}
                    onClick={() => setActiveItem('logout')}
                />
            ) : (
                <Menu.Item
                    name='login'
                    href='/login'
                    active={activeItem === 'login'}
                    onClick={() => setActiveItem('login')}
                />
            )}
        </Menu>
    );
};

export default Nav;
