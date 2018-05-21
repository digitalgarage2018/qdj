import React, { Component, PropTypes } from 'react';

class Checkbox extends React.Component{
  constructor(props) {
    super(props);

    this.state = {
      value : "",
      isChecked: false
    }
  }
 

  toggleCheckboxChange = () => {
    const { handleCheckboxChange, label } = this.props;

    this.setState(({ isChecked }) => (
      {
        isChecked: !isChecked,
      }
    ));

    if(this.state.isChecked) this.setState({value: this.props.value})
  }

  render() {
    const { isChecked } = this.state;
    return (
      <div className="checkbox">
        <label>
          <input
              type="checkbox"
              checked={isChecked}
              onChange={this.toggleCheckboxChange}
          />
        </label>
      </div>
    );
  }
}

export default Checkbox;