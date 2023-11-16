import PropTypes from "prop-types";
import React from "react";
import "./style.css";

export const MarketButtonWrapper = ({className, icnSvgIcnIcnLg = "/img/icn-svg-icn-icn-lg.svg"}: { className: string, icnSvgIcnIcnLg: string }) => {
    return (
        <div className={`market-button-wrapper ${className}`}>
            <img className="icn-svg-icn-icn-lg" alt="Icn svg icn icn lg" src={icnSvgIcnIcnLg}/>
            <div className="text-button-2">
                <div className="text-wrapper-3">Google Play</div>
            </div>
        </div>
    );
};

MarketButtonWrapper.propTypes = {
    icnSvgIcnIcnLg: PropTypes.string,
};
