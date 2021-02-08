import { useState } from 'react';
import { Menu } from 'semantic-ui-react';

const Nav = () => {
    const [activeItem, setActiveItem] = useState('home');

    return (
        <Menu size='huge' secondary>
            <Menu.Item header href='/'>
                Transact
            </Menu.Item>
            <Menu.Item
                name='home'
                href='/'
                active={activeItem === 'home'}
                onClick={() => setActiveItem('home')}
            />
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
                <Menu.Item
                    name='login'
                    href='/login'
                    active={activeItem === 'login'}
                    onClick={() => setActiveItem('login')}
                />
            </Menu.Menu>
        </Menu>
    );
};

export default Nav;
