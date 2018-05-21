import React from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import {Link} from 'react-router-dom';

export const NavBar = (props) => {

    console.log(props.type);

    return (
        <Navbar>
            <Navbar.Header>
                <Navbar.Brand>
                    <h4>Università Telematica Unimarina</h4>
                </Navbar.Brand>
            </Navbar.Header>
            { props.type != "S" ? "si" : "no"} 
                <Nav>
                    <NavItem eventKey={1} href="#">
                        <Link to={`/`}>LOGIN</Link>
                    </NavItem>
                    <NavItem eventKey={2} href="#">
                        <Link to={`/studentHome`}>STUDENT HOME</Link>
                    </NavItem>
                    <NavItem eventKey={3} href="#">
                        <Link to={`/professorHome`}>PROFESSOR HOME</Link>
                    </NavItem>
                </Nav>
                
            
            }
        </Navbar>
    )
};