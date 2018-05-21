import PropTypes from 'prop-types'
import React from 'react';
import { Button, Modal, Panel } from 'react-bootstrap';

export const StudentExam = (props) => {
    
    return (
        <div>
            <Panel eventKey={props.id} bsStyle="info">
                <Panel.Heading>
                    <Panel.Title toggle>
                        <div align='left' >{props.name} </div>   
                    </Panel.Title>
                    <div align='right'>Crediti: {props.credits} </div> 
                    <div align='right'>Voto: {props.mark ? props.mark+"/33" : "--/33"}</div>
                </Panel.Heading>
                <Panel.Body collapsible onClick={props.expand}>
                    {props.description}
                    <br></br>
                    <button onClick= {props.expand}>asd</button>     
                </Panel.Body>
            </Panel>
        </div>
    );
};

StudentExam.propTypes = {
    id: PropTypes.number,
    name: PropTypes.string,
    description: PropTypes.string,
    credits: PropTypes.number,
    mark: PropTypes,
    expand: PropTypes.string
};

export default StudentExam;