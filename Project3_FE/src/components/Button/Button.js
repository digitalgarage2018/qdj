import PropTypes from 'prop-types'
import './Button.css';
import React from 'react';

export const Button = (props) => {
    return <button type={props.type} disabled={props.disable} onClick={props.customOnClick}>{props.title}</button>
};

Button.propTypes = {
    type: PropTypes.string,
    title: PropTypes.string,
    customOnClick: PropTypes.func,
    disable: PropTypes.bool
};

export default Button