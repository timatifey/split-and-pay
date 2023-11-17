import React from 'react';
import './App.css';
import {IndexScreen} from "./screens/Index";
import {Route, Routes} from "react-router-dom";
import {HomeScreen} from "./screens/Home/HomeScreen";
import {RoomScreen} from "./screens/RoomScreen/RoomScreen";

const App: React.FC = () => {
    return (
        <Routes>
            <Route path="/" element={<IndexScreen/>}/>
            <Route path="/home" element={<HomeScreen/>}/>
            <Route path={"/room/:roomId"} element={<RoomScreen/>}/>
        </Routes>
    );
}

export default App;
