import React from 'react';
import ReactDOM from 'react-dom';

import 'semantic-ui-css/semantic.min.css';

import App from './App';

export class MinanceApp extends HTMLElement {
    connectedCallback() {
        console.log('mf-minance connected');
        this.render();
    }

    disconnectedCallback() {
        console.log('mf-minance disconnected');
    }

    render() {
        ReactDOM.render(<App />, this);
    }
}
