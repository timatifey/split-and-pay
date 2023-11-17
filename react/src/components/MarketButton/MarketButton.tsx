/*
We're constantly improving the code you see. 
Please share your feedback here: https://form.asana.com/?k=uvp-HPgd3_hyoXRBw1IcNg&d=1152665201300829
*/

import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const MarketButton = ({className, vector = "/img/vector-1.svg"}: { className: string, vector: string }) => {
    return (
        <div className={`market-button ${className}`}>
            <img className="vector" alt="Vector" src={vector}/>
            <div className="text-button">
                <div className="text-wrapper">Download on the</div>
                <div className="div">App Store</div>
            </div>
        </div>
    );
};

MarketButton.propTypes = {
    vector: PropTypes.string,
};
