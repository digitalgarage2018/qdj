import React from 'react';
import   axios from 'axios';
import { InfoComponent } from './StudentComponentPage/InfoComponent';
import { Button, Collapse, Well, From} from 'react-bootstrap';
import { Exam } from './StudentComponentPage/Exam';
import { CardItem } from '../../components/CardItem/CardItem';
import { List } from 'semantic-ui-react';
import Checkbox from '../../components/Checkbox/Checkbox';


export default class StudentHomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            open: false,
            user : JSON.parse(sessionStorage.getItem("user")),
            //exam : JSON.parse(sessionStorage.getItem("user")).exams
            exam : ["exam1","exam2"]
        }

        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleBooklet = this.handleBooklet.bind(this);

    }

    componentDidMount(){
        const user = JSON.parse(sessionStorage.getItem("user"));
        console.log(user.name);

    }

    createExam = exam => (
        <Exam
                name={exam.name}
                description={exam.description}
                credits={exam.credits}
            />
      )
      createExams = () => (
        this.state.exam.map(this.createExam)
      )
    

    handleSubmit(e) {
        console.log(sessionStorage.getItem("user"));
    }

    handleBooklet(e){
        const requestOptions = {
            headers: { 
                'Content-Type': 'application/json; charset=UTF-8',
                'jwt': sessionStorage.getItem("jwt")
            }
        };
        /*axios.get('http://localhost:8070/getAllExams', requestOptions )
            .then((res) => {
                console.log(res.headers.jwt);
                console.log(res.data);
            })
            .catch(err => {
                console.error(err);
            });*/
        this.setState({ open: !this.state.open });
    }

    render() {
        return (
            <div className="jumbotron">
                <InfoComponent name={this.state.user.name} surname={this.state.user.surname} matricola="1" institutional_email={this.state.user.institutional_email}>
                </InfoComponent>
                {this.state.exam != "" ? 
                    <div>
                    <Button onClick={this.handleBooklet}>
                      Crea Piano di Studio
                    </Button>
                    <Collapse in={this.state.open}>
                      <div>
                        <Well>
                            <List>
                                <List.Item>
                                    <Exam dis="true" name="Economia" description="asd" credits="8"/>
                                </List.Item>
                                <List.Item>                            
                                    <Exam dis="true" name="Economia" description="asd" credits="8"/>
                                </List.Item>
                            </List>
                            <form>
                            {this.createExams()}

                            </form>
                            
                        </Well>
                      </div>
                    </Collapse>
                  </div> 
                : 
                    <div>
                      {this.state.exam[0]}
                      {this.state.exam[1]}
                    </div>
                }
            </div>
        );
    }
}