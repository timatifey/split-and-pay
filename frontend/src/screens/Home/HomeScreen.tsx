import React, {useEffect, useState} from "react";
import "./style.css";
import {FormInput} from "../../components/FormInput/FormInput";
import {ListItem} from "../../components/ListItem/ListItem";
import {useNavigate} from "react-router-dom";
import roomService from "../../service/RoomService";
import RoomDto from "../../model/RoomDto";

export const HomeScreen = () => {
    const [rooms, setRooms] = useState<Array<RoomDto>>([])
    let navigate = useNavigate()

    useEffect(() => {
        if (localStorage.getItem('userId') == null) {
            navigate('/')
        } else {
            roomService.getRooms().then(r => {
                setRooms(r.data)
            })
        }
    }, [navigate]);

    return (
        <div className="frame">
            <div className="div-2">
                <div className="text-wrapper-home">Разделяй и плати</div>
                <div className="breadcrumb">
                    <div className="link">Home</div>
                    <img
                        className="topcoat-next-light"
                        alt="Topcoat next light"
                        src="https://c.animaapp.com/enS2D4jy/img/topcoat-next-light.svg"
                    />
                </div>
                <div className="h-3">Ваши комнаты:</div>
                <div className="h-4">
                    Привет, {localStorage.getItem('userId')}!
                </div>
                <div className="list-item list-item-5">
                    {rooms.map(roomDto =>
                        (<div className="room-div">
                            <img className="line" alt="Line" src="https://c.animaapp.com/bpMu8hJi/img/line-4-1.svg"/>
                            <div className="h">{roomDto.name}</div>
                            <div className="div-wrapper list-item-instance">
                                <div className="text-wrapper">3 days ago</div>
                            </div>
                            <p className="paragraph list-item-5-instance">
                                owner: {roomDto.owner.username}
                            </p>
                        </div>)
                    )}
                </div>
                <ListItem
                    className="list-item-5"
                    frameClassName="list-item-instance"
                    frameClassNameOverride="design-component-instance-node"
                    hasFrame={false}
                    paragraphClassName="list-item-5-instance"
                    text="Room#2"
                    text1={
                        <>
                            owner: Плетнев Тимофей
                            <br/>
                            totalReceipt: 500.0
                        </>
                    }
                    text2={
                        <>
                            owner: Плетнев Тимофей
                            <br/>
                            totalReceipt: 500.0
                        </>
                    }
                />
                <div className="div-3">
                    <div className="room-buttons">
                        <button className="btn-square btn-19">
                            <div className="btn-text">"Создать новую"</div>
                        </button>
                        <button className="btn-square btn-20">
                            <div className="btn-text">"Присоедениться"</div>
                        </button>
                    </div>
                    <FormInput className="form-input-14" text="Номер комнаты*"/>
                </div>
            </div>
        </div>
    );
};

