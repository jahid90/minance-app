import { useState } from 'react';
import { Link } from 'react-router-dom';
import { Menu } from 'semantic-ui-react';

import { useAppContext } from '../context/AppContextProvider';

const Nav = () => {
    const [activeItem, setActiveItem] = useState('home');

    const { token: loggedIn } = useAppContext();

    return (
        <Menu size='huge'>
            <Menu.Item header as={Link} to='/'>
                Transact
            </Menu.Item>
            <Menu.Item
                as={Link}
                to='/past-events'
                name='past events'
                active={activeItem === 'past-events'}
                onClick={() => setActiveItem('past-events')}
            />
            <Menu.Menu position='right'>
                <Menu.Item
                    as={Link}
                    to='/about'
                    name='about'
                    active={activeItem === 'about'}
                    onClick={() => setActiveItem('about')}
                />
                {loggedIn ? (
                    <Menu.Item
                        as={Link}
                        to='/profile'
                        name='profile'
                        active={activeItem === 'profile'}
                        onClick={() => setActiveItem('profile')}
                    />
                ) : (
                    <Menu.Item
                        as={Link}
                        to='/login'
                        name='login'
                        active={activeItem === 'login'}
                        onClick={() => setActiveItem('login')}
                    />
                )}
            </Menu.Menu>
        </Menu>
    );
};

export default Nav;
