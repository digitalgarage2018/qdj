import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem, Table, Panel, Grid, Row, Col} from 'react-bootstrap';
import "./UserInfo.css";

export const UserInfo  = (props) => {
  return (
    <div className="jumbotron" >
    <Panel bsStyle="primary">
      <Panel.Heading>
        <Panel.Title componentClass="h3">Info {props.type}</Panel.Title>
      </Panel.Heading>
      <Panel.Body>
        <Grid>
          <Row className="show-grid">
            <Col xs={6} md={4}>
              <div>Nome: </div>
              <div>Cognome: </div>
              <div>Matricola: </div>
              <div>Email Istituzionale: </div>
              <div>Email Personale: </div>
            </Col>
            <Col xs={6} md={4}>
              <div>{props.name}</div>
              <div>{props.surname}</div>
              <div>{props.matricola}</div>
              <div>{props.institutional_email}</div>
              <div>{props.personal_email}</div>
            </Col>
          </Row>
        </Grid>
      </Panel.Body>
    </Panel>
    </div>
  )
};
