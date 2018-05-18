import PropTypes from 'prop-types';
import React from 'react';
import { Card, Icon } from 'semantic-ui-react';

export const CardItem = (props) => {
return (
  <Card>
    <Card.Content header={props.title} />
    <Card.Content description={props.description} />
    <Card.Content extra>
      Crediti: {props.extra}
    </Card.Content>
  </Card>
)
}

CardItem.propTypes = {
    title: PropTypes.string,
    description: PropTypes.string,
    extra: PropTypes.number,
};


