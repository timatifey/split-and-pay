/*
We're constantly improving the code you see.
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import React, {ReactElement} from "react";
import "./style.css";

interface ListItemConf {
    className: string,
    text: string
    frameClassName: string,
    paragraphClassName: string
    text1: ReactElement
    text2: ReactElement
    frameClassNameOverride: string
    hasFrame: boolean
}

export const ListItem = ({
                             className,
                             text = "List group item heading",
                             frameClassName,
                             paragraphClassName,
                             text1,
                             text2,
                             frameClassNameOverride,
                             hasFrame
                         }: ListItemConf) => {
    return (
        <div className={`list-item ${className}`}>
            <div className="div">
                <img className="line" alt="Line" src="https://c.animaapp.com/bpMu8hJi/img/line-4-1.svg"/>
                <div className="h">{text}</div>
                <div className={`div-wrapper ${frameClassName}`}>
                    <div className="text-wrapper">3 days ago</div>
                </div>
                <p className={`paragraph ${paragraphClassName}`}>
                    {/* @ts-ignore*/}
                    {text1}
                </p>
            </div>
            <div className="frame-2">
                <div className="h-2">{text}</div>
                <p className="p">
                    {/* @ts-ignore*/}
                    {text2}
                </p>
                <div className={`frame-3 ${frameClassNameOverride}`}>
                    <div className="text-wrapper-2">3 days ago</div>
                </div>
            </div>
            {hasFrame && (
                <div className="frame-2">
                    <div className="h-2">{text}</div>
                    <p className="p">
                        {/* @ts-ignore*/}
                        {text2}
                    </p>
                    <div className="frame-3">
                        <div className="text-wrapper-2">3 days ago</div>
                    </div>
                </div>
            )}
        </div>
    );
};

