'use strict';
import React, {Component} from "react";
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import HomePage from "./pages/HomePage";
import ReactDOM from "react-dom";
import ProductFilter from "./pages/ProductFilter";
import CartPage from "./pages/CartPage";
import PaymentPage from "./pages/PaymentPage";
import ConfirmationPage from "./pages/ConfirmationPage";

class App extends Component {
    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={HomePage}/>
                    <Route path='/products' exact={true} component={ProductFilter}/>
                    <Route path='/cart' exact={true} component={CartPage} />
                    <Route path='/payment' exact={true} component={PaymentPage} />
                    <Route path='/order' exact={true} component={ConfirmationPage} />
                </Switch>
            </Router>
        )
    }
}

ReactDOM.render(<App />, document.getElementById("rootElement"));