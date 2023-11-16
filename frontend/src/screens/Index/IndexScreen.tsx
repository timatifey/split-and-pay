import React, {ChangeEvent, useEffect, useState, useTransition} from "react";
import {BtnLg} from "../../components/BtnLg";
import {MarketButton} from "../../components/MarketButton";
import {MarketButtonWrapper} from "../../components/MarketButtonWrapper";
import "./style.css";
import {FormGroup} from "../../components/FormGroup/FormGroup";
import userService from "../../service/UserService";
import UserDto from "../../model/UserDto";
import {useNavigate} from "react-router-dom";

export const IndexScreen: React.FC = () => {
    const [, startTransition] = useTransition();
    const [tab, setTab] = useState('greetings');
    const [invalidInput, setInvalidInput] = useState(false)
    const [name, setName] = useState("")

    let navigate = useNavigate()

    useEffect(() => {
        if (localStorage.getItem('userId') != null) {
            navigate('/home')
        }
    }, [navigate]);


    function selectTab(nextTab: string) {
        startTransition(() => {
            setTab(nextTab);
        });
    }

    const onChangeName = (e: ChangeEvent<HTMLInputElement>) => {
        const searchFileNameValue = e.target.value;
        setInvalidInput(searchFileNameValue === '')
        setName(e.target.value)
    };

    let greetings = (
        <div className="div-2">
            <p className="p">Приложение Для Совместного Разделения Чека</p>
            <div className="text-wrapper-4">Разделяй и плати</div>
            <BtnLg className="btn-lg-15" text="Попробовать сейчас!" onClickHandler={() => {
                selectTab('enterName')
            }}/>
            <MarketButton className="market-button-instance" vector="/img/vector.svg"/>
            <MarketButtonWrapper
                className="design-component-instance-node"
                icnSvgIcnIcnLg="/img/icn-svg-icn-icn-lg-1.svg"
            />
        </div>
    );
    let enterName = (
        <div className="div">
            <div className="text-wrapper">Разделяй и плати</div>
            <FormGroup invalid={invalidInput} onChangeHandler={onChangeName} value={name} className="form-group-1"
                       text="Ваше имя*"
                       text1="Имя *"
                       text2="Нам нужно как-то вас различать :)"/>
            <BtnLg className="btn-lg-1" text="Начать" onClickHandler={() => {
                if (name === '') {
                    setInvalidInput(true)
                    return
                }
                userService.createUser(new UserDto(name)).then((r) => {
                    localStorage.setItem('userId', r.data.id)
                    localStorage.setItem('userName', name)
                    navigate('/home')
                }, (error) => {
                    console.log(error)
                })
            }}/>
            <button className="btn btn-10" onClick={() => {
                userService.generateRandomName().then((r) => {
                    setInvalidInput(false)
                    setName(r.data.username)
                })
            }}>
                <div className="btn-text btn-instance">Случайное имя</div>
            </button>
        </div>
    );
    return <div className="frame">
        {tab === 'greetings' && greetings}
        {tab === 'enterName' && enterName}
    </div>;
};
