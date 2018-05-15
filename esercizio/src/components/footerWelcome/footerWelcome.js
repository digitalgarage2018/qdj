import React from "react";
import footerCss from "../../css/mainLayout/footer/footer.css";
import Logo from "../../images/logoUniMarinaFooter.png";

export default class FooterWelcome extends React.Component{
    constructor(props){
        super(props);
    }
    render(){
        return (  
            <div>
                <h1>Benvenuto ${requestScope['student'].name}</h1>
                <div className = "footer">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3 col-sm-6 ">
                                <div><img src={Logo} className = "logo-image"/></div>
                                <ul class="list-unstyled comp-desc-f">
                                    <li><p>      Investi sul tuo futuro! 
                                    Iscriviti al nostro corso di laurea online 
                                    </p></li> 
                                </ul> 
                            </div>
                            <div class="col-md-3 col-sm-6 ">
                    
                            </div>
                            <div class="col-md-3 col-sm-6 ">
                                <div class="heading-footer"><h2>Link utili</h2></div>
                                <ul class="list-unstyled link-list">

                                    <li><a href="about.html">Chi siamo</a></li> 
                                    <li><a href="project.html">Esami</a></li> 
                        
                                </ul>
                            </div>
                            <div class="col-md-3 col-sm-6">
                                <div class="heading-footer"><h2>Ci trovi qui</h2></div>
                                <address class="address-details-f">
                                    Giovanni Pezzotti, 8 Milano (IT)
                                    Telefono: 02 123 3456 
                        
                                    Email: <a href="mailto:info@unimarina.it" class="">info@unimarina.it</a>
                                    
                                </address>  
                    
                            </div>
                    
                        </div>
                    </div>
                    <div id="footer-bottom">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <div id="footer-copyrights">
                                        <p>Copyrights &copy; 2018 All Rights Reserved by Unimarina. <a href="#">Privacy Policy</a> <a href="#">Terms of Services</a></p>
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                    <a href="#home" id="back-to-top" class="btn btn-sm btn-green btn-back-to-top smooth-scrolls hidden-sm hidden-xs" title="home" role="button">
                        <i class="fa fa-angle-up"></i>
                    </a>
                </div> 
            </div> 
        );
    }
}