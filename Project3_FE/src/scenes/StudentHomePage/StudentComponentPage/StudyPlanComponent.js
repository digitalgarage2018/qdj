import React from 'react';
import { Checkbox } from 'semantic-ui-react';

export const StudyPlanComponent = (props) => {
    
    return (
        <div>
            <Checkbox>
                My Profile!
            </Checkbox>
        </div>
    )
}
/*
class StudyPlanComponent extends React.Component {
    constructor(props, context) {
      super(props, context);
  
      this.state = {
        open: false
      };
    }
  
    render() {
      return (
        <div>
          <Button onClick={() => this.setState({ open: !this.state.open })}>
            click
          </Button>
          <Collapse in={this.state.open}>
            <div>
              <Well>
                Anim pariatur cliche reprehenderit, enim eiusmod high life
                accusamus terry richardson ad squid. Nihil anim keffiyeh
                helvetica, craft beer labore wes anderson cred nesciunt sapiente
                ea proident.
              </Well>
            </div>
          </Collapse>
        </div>
      );
    }
}*/