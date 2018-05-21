import React from 'react';
import   axios from 'axios';
import { InfoComponent } from './ProfessorComponentPage/InfoComponent';
import { Button, Table, Glyphicon } from 'react-bootstrap';
import { ProfessorExam } from './ProfessorComponentPage/ProfessorExam';
import { Link } from 'react-router-dom';


export default class ProfessorHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            open : false,
            user : JSON.parse(sessionStorage.getItem("user")),
            exam : [],
            show: false 
        }
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
                this.setState({ exam: res.data.results.exams });
            })
            .catch(err => {
                console.error(err);
            });
        this.setState({ open: !this.state.open });
    }


    createExams = () => (
        ((this.state.exam!=null) ? this.state.exam.map(this.createExamSP) : "Nessun incarico assegnato")
    )
    
    createExamSP = (exam) => (
        <tr>
            <td> 
                <ProfessorExam 
                    id={exam.id_exam}
                    name={exam.name}
                    description={exam.description}
                    credits={exam.credits}
                />           
            </td>
            <td> 
                <Link to="/register" bsStyle="success">
                    <Button id={exam.id_exam} value={exam.id_exam} bsStyle="success">
                        <Glyphicon glyph="upload" /> Carica Materiale
                    </Button>
                </Link>
                <Link to="/studentHome" bsStyle="success">
                    <Button id={exam.id_exam} value={exam.id_exam} bsStyle="success">
                        <Glyphicon glyph="edit" /> Apri Appello
                    </Button> 
                </Link>    
            </td>
        </tr>     
    )

    render() {
        return (
            <div className="jumbotron">
                <InfoComponent name={this.state.user.name} surname={this.state.user.surname} matricola={this.state.user.matricola} institutional_email={this.state.user.institutional_email} personal_email={this.state.user.personal_email}>
                </InfoComponent>
                <div>
                        <Table responsive>
                            <thead>
                                <tr>
                                    <th>Incarichi Assegnati </th>
                                    <th>Azioni</th>
                                </tr>
                            </thead>
                            <tbody>
                                {this.createExams()}
                            </tbody>
                        </Table>
                    </div>
            </div>
        );
    }
}