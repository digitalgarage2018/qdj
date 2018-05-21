import PropTypes from 'prop-types'
import React from 'react';
import { Button, Modal, Panel } from 'react-bootstrap';

export const ProfessorExam = (props) => {
    
    return (
        <div>
            <Panel eventKey={props.id} bsStyle="info">
                <Panel.Heading>
                    <Panel.Title toggle>
                        <div align='left' >{props.name} </div>   
                    </Panel.Title>
                    <div align='right'>Crediti: {props.credits} </div> 
                </Panel.Heading>
                <Panel.Body collapsible>
                    {props.description}
                    {props.expand}
                </Panel.Body>
            </Panel>
        </div>
    );
};

ProfessorExam.propTypes = {
    id: PropTypes.number,
    name: PropTypes.string,
    description: PropTypes.string,
    credits: PropTypes.number,
    expand: PropTypes.string
};

export default ProfessorExam;