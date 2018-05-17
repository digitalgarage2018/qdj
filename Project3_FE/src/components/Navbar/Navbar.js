import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export const NavBar = () => {
    return (
        <Navbar>
            <Navbar.Header>
                <Navbar.Brand>
                    <h4>Università Telematica Unimarina</h4>
                </Navbar.Brand>
            </Navbar.Header>
            <Nav>
                <NavItem eventKey={1} href="#">
                    <Link to={`/`}>FIRST</Link>
                </NavItem>
                <NavItem eventKey={3} href="#">
                    <Link to={`/login`}>LOGIN</Link>
                </NavItem>
                <NavItem eventKey={4} href="#">
                    <Link to={`/studentHome`}>STUDENT HOME</Link>
                </NavItem>
                <NavItem eventKey={5} href="#">
                    <Link to={`/professorHome`}>PROFESSOR HOME</Link>
                </NavItem>
            </Nav>
        </Navbar>
    )
};