import PropTypes from 'prop-types'
import './Button.css';
import React from 'react';

export const Button = (props) => {
    return <button className='red-button' disabled={props.disable} onClick={props.customOnClick}>{props.title}</button>
};

Button.propTypes = {
    title: PropTypes.string,
    customOnClick: PropTypes.func,
    disable: PropTypes.bool
};