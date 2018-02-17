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
  order(e) {
	    var currentUser = document.getElementById('userInput').value; 
	    var city = axios.post('/api/order', { pizzaId: e, userName: currentUser }).then(res => {
	    	console.log(res);
	    });
  }
  
  render() {
    return (
      <div>
        <h1>Liste des pizzas</h1>
          <div className="container-fluid">
            <div className="card-columns col-md-8">
            {this.state.pizzas.map((p, i) =>
              <div className="card">
                  <div className="card-block">
                    <h4 className="card-title">{p.Name}</h4>
                    <p className="card-text">{p.Desc}</p>
                    <p className="card-text text-right">{p.price} Eur</p>
                  </div>
                  <div className="card-footer text-center">
                    <a onClick={() => this.order(p.pizzaId)} className="btn btn-primary">Commander</a>
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
