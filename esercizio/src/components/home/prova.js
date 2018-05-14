import React from "react";
import FirstSlideImage from "../../images/home-banner-bg.jpg";
import Logo from "../../images/logoUniMarina.png";
import homeCss from "../../css/home/home.css";
import {Icon} from "react-fa"
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

export default class Prova extends React.Component{
    constructor() {
        super();
     
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
            <div className = "home-content">
                <div>
                    <img className="d-block w-100" src={FirstSlideImage} alt="First slide"/>
                </div>        
                <div className="container">
                    <div className="row title-bar">
                        <div className="col-md-12">
		                    <center>
                                <img src={Logo} className= "logo-image"/>
		                    </center>
                            <p className="wow fadeInUp" data-wow-delay="0.4s">L'università telematica di Economia e Management, con la sua offerta didattica, mira a rispondere alla esigenza di formazione professionale e scientifica della società e del mercato del lavoro relativamente ai settori aziendale, economico in senso lato e statistico. Gli studenti possono approfondire le proprie conoscenze nell'ambito di quattro aree principali: economica, economico-aziendale, statistico-matematica e giuridica.</p>
                        </div>
                    </div>
                </div>  
                <p>
	                <br></br>
	                <br></br>
	                <br></br>
	            </p>
                <div className="container-fluid">
                    <p className="desc"></p>
                    <div className="row"> 
                        <div className="col-md-4-bg-starship">
                            <p className="desc"></p>
                            <div className="about-content-box wow fadeInUp" data-wow-delay="0.3s">
                                <i src={Icon} className="fa fa-user-plus"></i>
                                <h5>Effettua la registazione</h5>
                                <p className="desc">Inserisci i tuoi dati personali, una email e una password</p>
                                <p className="desc">Ti verrà fornita una email istituzionale</p>
                                <div>
                                    <button align="center" onClick={this.openModal1}>Registrati</button>
                                    <Modal
                                    isOpen={this.state.modalIsOpen1}
                                    onAfterOpen={this.afterOpenModal}
                                    onRequestClose={this.closeModal1}
                                    style={customStyles}
                                    contentLabel="Example Modal"
                                    >
                            
                                    <h2 ref={subtitle => this.subtitle = subtitle}>Registrazione</h2>
                                    <div class="modal-body"> 
                                        <label for="username">Nome</label> 
                                        <input id="register_username" class="form-control" type="text" placeholder="" required></input>
                                        <label for="cognome">Cognome</label> 
							            <input id="register_surname" class="form-control" type="text" placeholder="" required></input>
                                        <label for="cognome">Data di nascita</label> 
                                        <input id="register_surname" class="form-control" type="date" placeholder="" required></input>
                                        <label for="register_email">Email</label> 
                                        <input id="register_email" class="form-control" type="text" placeholder="" required></input>
                                        <label for="register_password">Password</label> 
                                        <input id="register_password" class="form-control" type="password" placeholder="" required maxlength="8"></input>
                                    </div>
                                    <button onSubmit={this.closeModal1}>Submit</button>
                                    </Modal>
                                </div>
                                <p className="desc"></p>
                            </div>
                        </div> 
                        <div className="col-md-4-bg-chathams">
                        <p className="desc"></p>
                            <div className="about-content-box wow fadeInUp" data-wow-delay="0.5s">
                                <i src={Icon} className="fa fa-user-circle-o"></i>
                                <h5>Effettua il login</h5>
                                <p className="desc">Assicurati di aver correttamente inserito i dati</p>
                                <p className="desc">Al primo accesso dovrai creare il tuo piano di studi</p>
                                <div>
                                    <button align="center" onClick={this.openModal2}>Login</button>
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
                                    <row>
                                        
                                        <button align="left" onSubmit={this.closeModal2}>Accedi</button>
                                        <p></p>
                                       
                                        <button  align="right" onClick={() => this.setState({ modalIsOpen1:true, modalIsOpen2:false})}> Registrati</button>
                                        
                                    </row>
                                    </Modal>
                                </div>
                                <p className="desc"></p>
                            </div>
                        </div> 
                        <div className="col-md-4-bg-matisse">
                            <p className="desc"></p>
                            <div className="about-content-box wow fadeInUp" data-wow-delay="0.7s">
                                <i src={Icon} className="fa fa-list-ul"></i>
                                <h5>Crea il tuo piano di studi</h5>
                                <p className="desc">Scegli 10 esami tra i 30 disponibili</p>
                            </div>
                        </div> 
                    </div> 
                </div>  
                <p>
	                <br></br>
	                <br></br>
	                <br></br>
	            </p>     
                <div className = "what-we-do">
                    <div className = "container-fluid">
                        <div className="row">
                            <div className="col-md-3">
                                <h3>Iscrivendomi ad UniMarina, devo spostarmi in qualche sede?</h3>
                                 <p className="desc">Assolutamente NO! Si tratta di un'Università Telematica ed in qunanto tale puoi completare il tuo percorso di studi comodamente da casa. Di seguito puoi vedere in sintesi le nostre funzioni.</p>
                            </div>
                            <div className="col-md-9">
                                <div className="row">
                                    <div className="col-md-4  col-sm-6">
                                         <div className="what-we-desc">
                                            <i className="fa fa-briefcase"></i>
                                            <h6>Lezioni online</h6>
                                            <p className="desc">Ogni docente carica il video delle lezione e il materiale didattico sul sito web. </p>
                                        </div>
                                    </div>
                                    <div className="col-md-4  col-sm-6">
                                        <div className="what-we-desc">
                                            <i className="fa fa-shopping-bag"></i>
                                            <h6>Piano di studi</h6>
                                            <p className="desc">Ogni studente, dopo aver effettuato il primo login deve creare il piano di studi scegliendo 10 esami tra quelli proposti.  </p>
                                        </div>
                                    </div>
                                    <div className="col-md-4  col-sm-6">
                                        <div className="what-we-desc">
                                            <i className="fa fa-building-o"></i>
                                            <h6>Sessione d'esami</h6>
                                            <p className="desc">Al termine di ogni semestre inizierà la suddetta Sessione d'esami in cui sarà possibile prenotarsi agli appelli ed effettuare gli esami.</p>
                                        </div>
                                    </div>
                                    <div className="col-md-4  col-sm-6">
                                        <div className="what-we-desc">
                                            <i className="fa fa-hourglass-2"></i>
                                            <h6>Seduta di Laurea</h6>
                                            <p className="desc">Raggiunto il numero di crediti necessari sarà possibile prenotarsi alla seduta di Laurea per sostenere la prova finale. </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>  
        );
    }
}
