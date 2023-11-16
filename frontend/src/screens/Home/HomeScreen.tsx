import React from "react";
import "./style.css";
import {BtnLg} from "../../components/BtnLg";
import {FormInput} from "../../components/FormInput/FormInput";
import {ListItem} from "../../components/ListItem/ListItem";

export const HomeScreen = () => {
    return (
        <div className="frame">
            <div className="div-2">
                <div className="text-wrapper-3">Разделяй и плати</div>
                <div className="breadcrumb">
                    <div className="link">Home</div>
                    <img
                        className="topcoat-next-light"
                        alt="Topcoat next light"
                        src="https://c.animaapp.com/bpMu8hJi/img/topcoat-next-light.svg"
                    />
                </div>
                <div className="h-3">Ваши комнаты:</div>
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
                    <BtnLg className="btn-lg-1" text="Создать новую" onClickHandler={() => {
                    }}/>
                    <BtnLg className="btn-lg-1" text="Присоедениться" onClickHandler={() => {
                    }}/>
                    <FormInput className="form-input-14" text="Номер комнаты*"/>
                </div>
            </div>
        </div>
    );
};

