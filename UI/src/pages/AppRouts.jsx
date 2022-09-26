import React from 'react';
import {Routes, Route, redirect, NavLink} from 'react-router-dom';
import NavBar from "../components/navBar";
import Redirect from "../components/Redirect";


const AppRouts = () => {
    return (
        <div>
            <NavBar/>
            {/*<NavLink to={"/logout"}>LogOut</NavLink>*/}
            <Routes>
                <Route path={'/logout'} element={<Redirect url={"http://localhost:9000/logout"}/> }/>
            </Routes>
        </div>
    );
};
export default AppRouts;