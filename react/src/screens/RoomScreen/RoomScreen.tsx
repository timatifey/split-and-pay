import React, {ChangeEvent, useEffect, useState} from "react";
import "./style.css";
import {useNavigate, useParams} from "react-router-dom";
import roomService from "../../service/RoomService";
import RoomDto from "../../model/RoomDto";
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';
import {Tooltip} from "react-tooltip";


export const RoomScreen = () => {
    const {roomId} = useParams()
    const [room, setRoom] = useState<RoomDto>()
    let navigate = useNavigate()
    const [invalidCost, setInvalidCost] = useState<boolean>()
    const [cost, setCost] = useState<string>("")

    const [invalidProductName, setInvalidProductName] = useState<boolean>()
    const [productName, setProductName] = useState<string>("")

    const onChangeCost = (e: ChangeEvent<HTMLInputElement>) => {
        const searchFileNameValue = e.target.value;
        setInvalidCost(searchFileNameValue === '' || isNaN(+searchFileNameValue))
        setCost(e.target.value)
    };

    const onChangeProductName = (e: ChangeEvent<HTMLInputElement>) => {
        const searchFileNameValue = e.target.value;
        setInvalidProductName(searchFileNameValue === '')
        setProductName(e.target.value)
    };


    useEffect(() => {
        if (localStorage.getItem('userId') == null) {
            navigate('/')
        } else {
            if (!(typeof roomId != 'undefined' && roomId)) {
                navigate('/home')
                return
            }
            roomService.getRoom(roomId).then(r => {
                setRoom(r.data)
            }, (error) => {
                navigate('/home')
            })
        }
    }, [navigate, roomId]);

    return (
        <div className="frame">
            <div className="div-2">
                <div className="text-wrapper-home">Разделяй и плати</div>
                <div className="breadcrumb">
                    <div className="link" onClick={() => {
                        navigate('/home')
                    }}>
                        Home
                    </div>
                    <img
                        className="topcoat-next-light"
                        alt="Topcoat next light"
                        src="/img/arrow.svg"
                    />
                    <div className="h">{room?.name}#{room?.id}</div>
                </div>
                <div className="h-3">Чек:</div>
                <div className="h-totalsum">{room?.totalSum ?? 0.0}₽</div>
                <div className="h-4">
                    Привет, {localStorage.getItem('userName')}!
                </div>
                <div className="list-item list-item-5">
                    {
                        room?.receipt?.length === 0 ? (<div>
                                <div className="h-empty">Пока что пусто</div>
                            </div>) :
                            room?.receipt?.map((productDto, i) =>
                                (<div className="room-div" key={i}>
                                    <div className="room-name">{productDto.name}#{productDto.id}</div>
                                    <div className="room-manage-buttons">
                                        <button className="btn-square btn-21" onClick={() => {
                                            roomService.addUserToProduct(roomId!, productDto.id).then(r => {
                                                setRoom(r.data)
                                            }, (error) => {
                                                alert(error.response.data.message)
                                            })
                                        }}>
                                            <div className="btn-text">Добавить себя</div>
                                        </button>
                                        <button className="btn-square btn-22" onClick={() => {
                                            roomService.deleteUserFromProduct(roomId!, productDto.id).then(r => {
                                                setRoom(r.data)
                                            }, (error) => {
                                                alert(error.response.data.message)
                                            })
                                        }}>
                                            <div className="btn-text">Удалить себя</div>
                                        </button>
                                    </div>
                                    <div className="paragraph list-item-5-instance">
                                        amount: {productDto.amount}
                                    </div>
                                    <div className="paragraph list-item-5-instance">
                                        <Stack direction="row" spacing={2}>

                                            {productDto.users.map((userDto, i) => (
                                                <div key={i}>
                                                    <Avatar data-tooltip-id={`user-tooltip-${i}`}
                                                            sx={{bgcolor: "#" + Math.random().toString(16).substring(9)}}>
                                                        {userDto.shortName}
                                                    </Avatar>
                                                    <Tooltip
                                                        className="react-tooltip__place-left"
                                                        id={`user-tooltip-${i}`}
                                                        content={userDto.username}
                                                        place='bottom'
                                                    />
                                                </div>
                                            ))}
                                        </Stack>
                                    </div>
                                </div>)
                            )}
                </div>
                <div className="div-3">
                    <div className="room-buttons">
                        <button className="btn-square btn-20" onClick={() => {
                            if (typeof productName == 'undefined' || !productName) {
                                setInvalidProductName(true)
                                return
                            }
                            if (cost === '' || isNaN(+cost)) {
                                setInvalidCost(true)
                                return;
                            }
                            roomService.addProduct(roomId!, productName!, Number(cost)).then(r => {
                                setRoom(r.data)
                            }, (error) => {
                                alert(error.response.data.message)
                            })
                        }}>
                            <div className="btn-text">Добавить в чек</div>
                        </button>
                    </div>
                    <div className="form-input form-input-15">
                        <div className="input-form-control"
                             style={{borderColor: invalidProductName ? "#f30e0e" : "#e6e6e6"}}>
                            <input className="email" placeholder="Название продукта*"
                                   onChange={onChangeProductName}
                                   value={productName}/>
                        </div>
                        <div className="input-form-control input-form-control-2"
                             style={{borderColor: invalidCost ? "#f30e0e" : "#e6e6e6"}}>
                            <input className="email" placeholder="Стоимость*"
                                   style={{borderColor: invalidCost ? "#f30e0e" : "#e6e6e6"}}
                                   onChange={onChangeCost}
                                   value={cost}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

