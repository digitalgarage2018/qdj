import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem, Table} from 'react-bootstrap';
import "./UserInfo.css";

export const UserInfo  = (props) => {
  return (
    <div className="jumbotron" >
      <h2>Info {props.type}</h2>
      <Table className="jumbotron">
        <tr>
          <th>Nome: </th>
          <td>{props.name}</td>
        </tr>
        <tr>
          <th>Cognome: </th>
          <td>{props.surname}</td>
        </tr>
        <tr>
          <th>Matricola: </th>
          <td>{props.matricola}</td>
        </tr>
        <tr>
          <th>Email istituzionale</th>
          <td>{props.institutional_email}</td>
        </tr>
      </Table>
    </div>
  )
};
