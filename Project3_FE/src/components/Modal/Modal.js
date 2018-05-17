import PropTypes from 'prop-types'
import './Modal.css';
import React from 'react';

export const Modal = (props) => {
    return <Modal onClick={props.customOnClick}>{props.title}</Modal>
};

Modal.propTypes = {
    title: PropTypes.string,
    customOnClick: PropTypes.func
};