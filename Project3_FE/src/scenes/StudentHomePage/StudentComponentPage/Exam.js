import PropTypes from 'prop-types'
import React from 'react';
import { Panel } from 'react-bootstrap';
import { Button } from '../../../components/Button/Button';
import { Checkbox } from 'semantic-ui-react';

export const Exam = (props) => {
    return (
        <div>
            <Panel bsStyle="primary" >
                <Panel.Heading>
                <Panel.Title  componentClass="h3">{props.name}</Panel.Title>
                </Panel.Heading>
                <Panel.Body >{props.description}</Panel.Body>
                <Panel.Footer>
                    Crediti: {props.credits}
                    <Button bsStyle="info">Aggiungi</Button>
                    <Button disable={props.dis} title="Prenota" onClick={()=>{ }}></Button>
                    <Checkbox toggle label="Inserisci nel piano di studi"></Checkbox>
                </Panel.Footer>
            </Panel>
        </div>
    );
};

Exam.propTypes = {
    name: PropTypes.string,
    description: PropTypes.string,
    credits: PropTypes.number,
};