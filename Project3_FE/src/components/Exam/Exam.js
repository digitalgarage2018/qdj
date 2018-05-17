import PropTypes from 'prop-types'
import React from 'react';
import { Title } from '../Title/Title';
import './Exam.css';
import { Panel } from 'react-bootstrap';

export const Exam = (props) => {
    return (
        <div>
            <Panel bsStyle="primary" >
                <Panel.Heading>
                <Panel.Title  componentClass="h3">{props.name}</Panel.Title>
                </Panel.Heading>
                <Panel.Body >{props.description}</Panel.Body>
                <Panel.Footer>Crediti: {props.credits}</Panel.Footer>
            </Panel>
        </div>
    );
};

Exam.propTypes = {
    name: PropTypes.string,
    description: PropTypes.string,
    credits: PropTypes.number,
};