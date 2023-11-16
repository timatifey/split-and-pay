/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React from "react";
import "./style.css";

interface BtnLgConfig {
    className: string;
    text: string;
    onClickHandler: () => void
}

export const BtnLg = ({className, text = "Button", onClickHandler}: BtnLgConfig) => {
    return (
        <button className={`btn-lg ${className}`} onClick={() => {
            onClickHandler()
        }}>
            <div className="h">{text}</div>
        </button>
    );
};


