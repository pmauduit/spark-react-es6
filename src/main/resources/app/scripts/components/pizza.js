import React from 'react';
import ReactDOM from 'react-dom';
import axios from 'axios';

class PizzaComponent extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      pizzas: []
    };
  }

  componentDidMount() {
    axios.get('/api/pizzas')
      .then(res => {
        this.setState(res.data);
      });
  }

  render() {
    return (
      <div>
        <h1>Liste des pizzas</h1>
        <ul>
          {this.state.pizzas.map(p =>
               <li><b>{p.Name}</b> <i>{p.Desc}</i> <b>{p.price} Eur</b></li>
          )}
        </ul>
      </div>
    );
  }
}

export default PizzaComponent;