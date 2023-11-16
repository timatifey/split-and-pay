import React from 'react';
import './App.css';
import {IndexScreen} from "./screens/Index";
import {Route, Routes} from "react-router-dom";
import {HomeScreen} from "./screens/Home/HomeScreen";

const App: React.FC = () => {
    return (
        <Routes>
            <Route path="/" element={<IndexScreen/>}/>
            <Route path="/home" element={<HomeScreen/>}/>
        </Routes>
    );
}

export default App;
