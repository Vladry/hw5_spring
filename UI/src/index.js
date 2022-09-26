import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './pages/App';
import CssBaseline from '@material-ui/core/CssBaseline';   // reset CSS  https://material-ui.com/ru/components/css-baseline/
import Container from '@material-ui/core/Container'; //берем Material UI контейнер для глобального центрирования
// import {Provider} from "react-redux";
import {BrowserRouter} from 'react-router-dom';
import {createGlobalStyle} from "styled-components";
import ErrorBoundary from "./components/ErrorBoundary/ErrorBoundary";

const Global = createGlobalStyle`
a {
text-decoration: none;
}
.boxGenStyle {
border: 1px solid lightgray;
box-shadow: 4px 4px 8px 1px rgba(34, 60, 80, 0.2);
box-sizing: border-box;
}
`;

ReactDOM.render(
    <React.StrictMode>

        <BrowserRouter>
            {/*<Provider store={store}>*/}
            <CssBaseline>
                <ErrorBoundary>
                    <Container align='center'>
                        <Global/>
                        <App/>
                    </Container>
                </ErrorBoundary>
            </CssBaseline>
            {/*</Provider>*/}
        </BrowserRouter>

    </React.StrictMode>,
    document.getElementById('root')
);

