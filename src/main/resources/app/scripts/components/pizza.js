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
    		    <div className="container-fluid">
    		      <div className="card-deck">
          {this.state.pizzas.map((p, i) =>
             <div className="card" styles={{width : '20rem'}}>
             
                <div className="card-block">
                  <h4 className="card-title">{p.Name}</h4>
                  <p className="card-text">{p.Desc}</p>
                  <a href='#' className="btn btn-primary">Commander</a>
                </div>
              </div>
          )
          }
                </div>
              </div>
          </div>
    );
  }
}

export default PizzaComponent;