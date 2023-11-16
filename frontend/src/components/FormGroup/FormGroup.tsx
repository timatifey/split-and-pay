import PropTypes from "prop-types";
import React, {ChangeEventHandler} from "react";
import "./style.css";
import {Tooltip} from 'react-tooltip'
import 'react-tooltip/dist/react-tooltip.css'

interface FormGroupConfig {
    className: string,
    text: string,
    text1: string,
    text2: string
    invalid: boolean
    onChangeHandler: ChangeEventHandler<HTMLInputElement>
}

export const FormGroup = (
    {
        className,
        text,
        text1,
        text2,
        invalid,
        onChangeHandler
    }: FormGroupConfig
) => {
    return (
        <div className={`form-group ${className}`}>
            <div data-tooltip-id="my-tooltip" className="form-control">{text}</div>
            <div className="form-control-input">
                <div className="input-form-control" style={{borderColor: invalid ? "#f30e0e" : "#e6e6e6"}}>
                    <input className="email" placeholder={text1} onChange={onChangeHandler}/>
                </div>
            </div>
            <Tooltip
                className="react-tooltip__place-left"
                id="my-tooltip"
                content="Введите имя, пожалуйста, или сгенерируйте случайное ◕‿‿◕"
                isOpen={invalid}
                place='right-end'
            />
            <p className="form-text">{text2}</p>
        </div>
    );
};

FormGroup.propTypes = {
    text: PropTypes.string,
    text1: PropTypes.string,
    text2: PropTypes.string,
};