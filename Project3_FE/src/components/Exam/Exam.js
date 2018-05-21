import PropTypes from 'prop-types'
import React from 'react';
import { Panel } from 'react-bootstrap';

export const Exam = (props) => {
    return (
        <div>
            <Panel bsStyle="primary" >
                <Panel.Heading>
                <Panel.Title  componentClass="h3">{props.id} - {props.name}</Panel.Title>
                </Panel.Heading>
                <Panel.Body >{props.description}</Panel.Body>
                <Panel.Footer>
                    Crediti: {props.credits}
                </Panel.Footer>
            </Panel>
        </div>
    );
};

Exam.propTypes = {
    id: PropTypes.number,
    name: PropTypes.string,
    description: PropTypes.string,
    credits: PropTypes.number,
};