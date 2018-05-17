import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem, Table} from 'react-bootstrap';
import "./UserInfo.css";

export const UserInfo  = () => {
    return (
        <div className="jumbotron" >
        <h2>Dati Utente</h2>
        <Table className="jumbotron">
          <tr>
            <th>Nome: </th>
            <td>Bill Gates</td>
          </tr>
          <tr>
            <th>Cognome: </th>
            <td>555 77 854</td>
          </tr>
          <tr>
            <th>Matricola: </th>
            <td>555 77 855</td>
          </tr>
          <tr>
            <th>Email istituzionale</th>
            <td>555 77 855</td>
          </tr>
        </Table>
                </div>
      )
        };
