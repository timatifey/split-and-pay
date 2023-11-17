import React, {ChangeEvent, useEffect, useState} from "react";
import "./style.css";
import {useNavigate} from "react-router-dom";
import roomService from "../../service/RoomService";
import RoomDto from "../../model/RoomDto";

export const HomeScreen = () => {
    const [rooms, setRooms] = useState<Array<RoomDto>>([])
    let navigate = useNavigate()

    const [roomNumber, setRoomNumber] = useState("")
    const [, setInvalidRoomNumber] = useState(false)
    const [roomName, setRoomName] = useState("")
    const [invalidName, setInvalidName] = useState(false)


    useEffect(() => {
        if (localStorage.getItem('userId') == null) {
            navigate('/')
        } else {
            roomService.getRooms().then(r => {
                setRooms(r.data)
            }, (error) => {
                alert(error.response.data.message)
            })
        }
    }, [navigate]);

    const onChangeRoomNumber = (e: ChangeEvent<HTMLInputElement>) => {
        const searchFileNameValue = e.target.value;
        setInvalidRoomNumber(searchFileNameValue === '')
        setRoomNumber(e.target.value)
    };

    const onChangeRoomName = (e: ChangeEvent<HTMLInputElement>) => {
        const searchFileNameValue = e.target.value;
        setInvalidRoomNumber(searchFileNameValue === '')
        setRoomName(e.target.value)
    };

    return (
        <div className="frame">
            <div className="div-2">
                <div className="text-wrapper-home">Разделяй и плати</div>
                <div className="breadcrumb">
                    <div className="link">Home</div>
                    <img
                        className="topcoat-next-light"
                        alt="Topcoat next light"
                        src="/img/arrow.svg"
                    />
                </div>
                <div className="h-3">Ваши комнаты:</div>
                <div className="h-4">
                    Привет, {localStorage.getItem('userName')}!
                </div>
                <div className="list-item list-item-5">
                    {rooms.map((roomDto, i) =>
                        (<div className="room-div" key={i}>
                            <div className="room-name">{roomDto.name}#{roomDto.id}</div>
                            <div className="room-manage-buttons">
                                <button className="btn-square btn-21" onClick={() => {
                                    navigate(`/room/${roomDto.id}`)
                                }}>
                                    <div className="btn-text">Открыть</div>
                                </button>
                                <button className="btn-square btn-22" onClick={() => {
                                    roomService.deleteRoom(roomDto.id).then(r => {
                                        setRooms(r.data)
                                    }, (error) => {
                                        alert(error.response.data.message)
                                    })
                                }}>
                                    <div className="btn-text">Удалить</div>
                                </button>
                            </div>
                            <div className="paragraph list-item-5-instance">
                                owner: {roomDto.owner.username}
                            </div>
                            <div className="paragraph list-item-5-instance">
                                createdAt: {new Date(roomDto.createdAt + "Z").toISOString().replace("Z", "")}
                            </div>
                        </div>)
                    )}
                </div>
                <div className="div-3">
                    <div className="room-buttons">
                        <button className="btn-square btn-19" onClick={() => {
                            if (roomNumber === '') {
                                setInvalidRoomNumber(true)
                                return
                            }
                            roomService.connectToRoom(roomNumber).then(r => {
                                let newRooms = Array.from(rooms)
                                newRooms.push(r.data)
                                setRooms(newRooms)
                            }, (error) => {
                                alert(error.response.data.message)
                            })
                        }}>
                            <div className="btn-text">Присоединиться</div>
                        </button>
                        <button className="btn-square btn-20" onClick={() => {
                            if (roomName === '') {
                                setInvalidName(true)
                                return
                            }
                            roomService.createNew(roomName).then(r => {
                                let newRooms = Array.from(rooms)
                                newRooms.push(r.data)
                                setRooms(newRooms)
                            }, (error) => {
                                alert(error.response.data.message)
                            })
                        }}>
                            <div className="btn-text">Создать новую</div>
                        </button>
                    </div>
                    <div className="form-input form-input-14">
                        <div className="input-form-control">
                            <input className="email" type="number" placeholder="Номер комнаты*"
                                   onChange={onChangeRoomNumber}
                                   value={roomNumber}/>
                        </div>
                        <div className="input-form-control input-form-control-2">
                            <input className="email" placeholder="Имя комнаты*"
                                   style={{borderColor: invalidName ? "#f30e0e" : "#e6e6e6"}}
                                   onChange={onChangeRoomName}
                                   value={roomName}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

