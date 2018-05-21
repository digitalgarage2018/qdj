import React from 'react';
import axios from 'axios';
import { InfoComponent } from './StudentComponentPage/InfoComponent';
import { Exam } from '../../components/Exam/Exam';
import { Modal, Button, Well, Table, Collapse, Popover, OverlayTrigger, Glyphicon} from 'react-bootstrap';
import { StudentExam } from './StudentComponentPage/StudentExam';

export default class StudentHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            open : false,
            user : JSON.parse(sessionStorage.getItem("user")),
            exam : [],
            selectedExam : [],
            show: false 
        }

        this.createModal = this.createModal.bind(this);
        this.handleShow = this.handleShow.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleBooklet = this.handleBooklet.bind(this);
        this.toggleCheckboxChange = this.toggleCheckboxChange.bind(this);
        this.handleAddExam = this.handleAddExam.bind(this);
        this.getSessionByExam = this.getSessionByExam.bind(this);
    }


    componentDidMount(){
        const requestOptions = {
            headers: { 
                'Content-Type': 'application/json; charset=UTF-8',
                'jwt': sessionStorage.getItem("jwt"),
            }
        };

        const url = 'http://localhost:8070/getUserExams?user_id='+JSON.parse(sessionStorage.getItem("user")).matricola;
        
        axios.get(url,requestOptions )
            .then((res) => {
                //console.log(res.data.results.exams);
                this.setState({
                    exam: res.data.results.exams
                });
                //console.log(this.state.exam);
            })
            .catch(err => {
                console.error(err);
            });
        this.setState({ open: !this.state.open });
    }
    
    handleClose() {
        this.setState({ show: false });
    }
    
    handleShow() {
        this.setState({ show: true });
    }

    toggleCheckboxChange = (e) => {    
        //this.setState({book : e.target.value});
        //console.log(this.book);        
    }

    handleAddExam = (event) => {
        var exam = event.target.value;
        console.log("event : ",event, "-> exam : ",exam);
        console.log("array SelectedExam OLD = ",this.state.selectedExam);
        if(!this.state.selectedExam.includes(exam)){
            this.setState({ selectedExam : [...this.state.selectedExam, exam]})
            /*let array = [...this.state.selectedExam]; // make a separate copy of the array
            let l = array.push(exam);
            this.setState({ selectedExam : array});*/
            console.log("array SelectedExam after ADD = ",this.state.selectedExam);
        }else{
            var filteredArray = this.state.selectedExam.filter(item => item !== exam)
            this.setState({ selectedExam : filteredArray });
            /*let array = [...this.state.selectedExam]; // make a separate copy of the array
            let index = array.indexOf(exam);
            array.splice(index,1);
            this.setState({ selectedExam : array});*/
            console.log("array SelectedExam after REMOVE = ",this.state.selectedExam);
        }
    }

    createExam = exam => (
        <tr>
        <td>
        <OverlayTrigger
            trigger="click" 
            placement="left" 
            overlay={<Popover id="popover-trigger-left" title="Esame Aggiunto!">
                        <p>Cliccare</p>
                        <p>per rimuovere</p>
                        <p>dal piano di studio</p>
                    </Popover>
            }
        >
            <Button id={exam.id_exam} onClick={this.handleAddExam} value={exam.id_exam} bsStyle="info" ><Glyphicon glyph="plus" /> Aggiungi/ <Glyphicon glyph="minus" /> Rimuovi</Button>
        </OverlayTrigger> 
        </td>
        <td> 
            <Exam 
                id={exam.id_exam}
                name={exam.name}
                description={exam.description}
                credits={exam.credits}
            />
        </td>
        </tr>
    )

    createExams = () => (
    ((this.state.exam!=null) ? this.state.exam.map(this.createExamSP) : this.state.exam.map(this.createExam) )
    )

    createExamSP = (exam) => (
        <tr>
            <td> 
                <StudentExam 
                    id={exam.id_exam}
                    name={exam.name}
                    description={exam.description}
                    credits={exam.credits}
                    mark=""
                    expand={this.getSessionByExam}
                />           
            </td>
            <td> 
                <Button  
                    id={exam.id_exam} 
                    onClick={this.getSessionByExam} 
                    value={exam.id_exam} 
                    bsStyle="success"
                >
                    <Glyphicon glyph="search" /> Prenota Appello
                </Button>    
            </td>
        </tr>     
    )

    getSessionByExam = (exam_id) => {
        const requestOptions = {
            headers: { 
                'Content-Type': 'application/json; charset=UTF-8',
                'jwt': sessionStorage.getItem("jwt"),
            }
        };
        const url = 'http://localhost:8070/getAllSessions?exam_id='
            +JSON.parse(sessionStorage.getItem("user")).matricola
            +'&open=true';
        const mex = axios.get(url,requestOptions)
            .then((res) => {
                //console.log(res.headers.jwt);
                console.log(res.data.message);
                return res.data;
                //return "Nessun appello disponibile";
            })
            .catch(err => {
                console.error(err);
            });
        mex.then(function(result) {
                console.log(result); //will log results.
                return result.message; 
             })
    }

    createModal = (resuls) =>{
       return(
            <Modal.Dialog>
                <Modal.Header>
                    <Modal.Title>{this.result.message}</Modal.Title>
                </Modal.Header>

                <Modal.Body>One fine body...</Modal.Body>

                <Modal.Footer>
                    <Button>Close</Button>
                    <Button bsStyle="primary">Save changes</Button>
                </Modal.Footer>
            </Modal.Dialog>
        )
    }

    handleSubmit = (event) => {
        const requestOptions = {
            headers: { 
                'Content-Type': 'application/json; charset=UTF-8',
                'jwt': sessionStorage.getItem("jwt")
            }
        };

        const data = JSON.stringify({ 
            'user_id' : this.state.user.matricola,
            'exams' : this.state.selectedExam 
        });

        axios.post('http://localhost:8070/studyPlan', data, requestOptions )
            .then((res) => {
                console.log(res.data.results);
            })
            .catch(err => {
                console.error(err);
            });

        console.log("request ="+ data);
        console.log("book ="+this.state.selectedExam);
        console.log(this.state.selectedExam.length);
        console.log(this.state.selectedExam.length===10);
    }

    handleBooklet(e){
        const requestOptions = {
            headers: { 
                'Content-Type': 'application/json; charset=UTF-8',
                'jwt': sessionStorage.getItem("jwt")
            }
        };
        axios.get('http://localhost:8070/getAllExams', requestOptions )
            .then((res) => {
                //console.log(res.headers.jwt);
                console.log(res.data.results.exams);
                this.setState({
                    exam: res.data.results.exams
                })
            })
            .catch(err => {
                console.error(err);
            });
        this.setState({ open: !this.state.open });
    }



    render() {
        return (
            <div className="jumbotron">
                <InfoComponent name={this.state.user.name} surname={this.state.user.surname} matricola="1" institutional_email={this.state.user.institutional_email} personal_email={this.state.user.personal_email}>
                </InfoComponent>                      
                {this.state.exam === "" ? 
                    <div>
                        <Button onClick={this.handleBooklet} bsStyle="warning">
                            <Glyphicon glyph="list-alt" /> Crea Piano di Studio
                        </Button>
                        <Collapse in={this.state.open}>
                            <div>
                                <Well>
                                    <Table responsive>
                                        <thead>
                                            <tr>
                                            <th>Seleziona </th>
                                            <th>Lista Esami </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {this.createExams()}
                                        </tbody>
                                    </Table>
                                    <Button onClick={this.handleSubmit} disabled={(this.state.selectedExam.length!=10)} > 
                                        <Glyphicon glyph="pencil" /> Salva Piano di Studio 
                                    </Button>
                                </Well>
                            </div>
                        </Collapse>
                    </div> 
                : 
                    <div>
                        <Table responsive>
                            <thead>
                                <tr>
                                    <th>Libretto Universitario</th>
                                    <th>Azioni</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.createExams()}
                            </tbody>
                        </Table>
                    </div>
                }
            </div>
        );
    }
}