import React from "react";
import headerCss from "../../../css/mainLayout/header/header.css";
import Logo from "../../../images/logoUniMarina.png";
import Modal from 'react-modal';

const customStyles = {
    content : {
      top                   : '50%',
      left                  : '50%',
      right                 : 'auto',
      bottom                : 'auto',
      marginRight           : '-50%',
      transform             : 'translate(-50%, -50%)'
    }
  };

export default class Header extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            modal1IsOpen: false,
            modal2IsOpne: false
          };
       
          this.openModal1 = this.openModal1.bind(this);
          this.openModal2 = this.openModal2.bind(this);
          this.afterOpenModal = this.afterOpenModal.bind(this);
          this.closeModal1 = this.closeModal1.bind(this);
          this.closeModal2 = this.closeModal2.bind(this);
    }
       
    openModal1() {
        this.setState({modalIsOpen1: true});
    }
    openModal2() {
        this.setState({modalIsOpen2: true});
    }
  
    afterOpenModal() {
        // references are now sync'd and can be accessed.
    }
    closeModal1() {
        this.setState({modalIsOpen1: false});
    }
    closeModal2() {
        this.setState({modalIsOpen2: false});
    }
    
    render(){
        return (
            <div className="top-menubar">
                <div className="topmenu">
                    <div className="container">
                        <div className="row">
                            <div className="col-md-7">
                                <ul className="list-intline top-contacts">
					                <li>
						                <i className="fa fa-envelope"></i> Email:<a href="mailMarina">info@unimarina.it</a>
					                </li>
				                </ul>	
                            </div> 
                        <div className="col-md-5">
                            <div className="acc-reg-bttn">
                                <button onClick={this.openModal2}>Login</button>
                                <Modal
                                isOpen={this.state.modalIsOpen2}
                                onAfterOpen={this.afterOpenModal}
                                onRequestClose={this.closeModal2}
                                style={customStyles}
                                contentLabel="Example Modal"
                                >
                            
                                <h2 ref={subtitle => this.subtitle = subtitle}>Effettua il login</h2>
                                <div class="modal-body">
                                <label for="username">Email istituzionale</label> 
                                <input id="login_username" class="form-control" type="text" placeholder="" required></input>
                                <label for="username">Password</label> 
                                <input id="login_password" class="form-control" type="password" placeholder="" required></input>
                                </div>                                      
                                <button align="left" onSubmit={this.closeModal2}>Accedi</button>
                                
                                </Modal>
                                </div>
                        </div>
                    </div>
                </div>
            </div> 
        
            <nav class="navbar navbar-expand-lg navbar-light" id="mainNav" data-toggle="affix">
                <div class="container">
                    <a className="navbar-brand" href="#">
                        <img src={Logo} className = "logo-image"/>
                    </a>
		           
                    <button className="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"> 
                        <span className="navbar-toggler-icon"></span>
                    </button>  
                    <div className="collapse navbar-collapse" id="navbarResponsive">
                        <ul className="navbar-nav ml-auto">
                            <li className="nav-item" ><a class="nav-link smooth-scroll" href="index.html">Home</a></li>
                
				            <li className="nav-item dropdown" >
                                <a className="nav-link dropdown-toggle smooth-scroll" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Chi siamo</a> 
                                <div className="dropdown-menu dropdown-cust" aria-labelledby="navbarDropdownMenuLink">
                                    <a className="dropdown-item" href="about.html">I nostri servizi</a>
                                    <a className="dropdown-item" href="about.html">I nostri Docenti</a>
                                </div>
                            </li>
                        </ul>  
                    </div>
                </div>
            </nav>
        </div>
        );
    }
}