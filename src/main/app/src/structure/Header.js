import React, {Component} from "react";
import CartQuantities from "../helpers/CardSection";
import axios from "axios";

class Header extends Component{
    constructor(props) {
        super(props);
        this.state = {
            productItems:[],
            categories:''
        }
    }

    componentDidMount() {
        const CARTS_REST_API_URL = '/awf/ecommerce/rest/productItems';
        const fetchCarts = async () => {
            try {
                const res = await axios.get(CARTS_REST_API_URL)
                this.setState({productItems: res.data});
            }catch (err){
                console.log(err)
            }
        }
        fetchCarts().then()
        this.categoriesData()
    }

    cartQuantity = (productItems) => {
        let total = 0
        productItems.map((productItem, index) => {
            total+=productItem.quantity
        })
        return total
    }

    categoriesData = () =>{
        const CATEGORIES_REST_API_URL = '/awf/ecommerce/rest/categories';
        const fetchCategories = async () => {
            try {
                const res = await axios.get(CATEGORIES_REST_API_URL)
                this.setState({categories: res.data.categories});
            }catch (err){
                console.log(err)
            }
        }
        fetchCategories().then()
    }

    render() {
        return (
                 <header className="section-header">
                    <section className="header-top-light border-bottom">
                        <div className="container">
                            <nav className="d-flex flex-column flex-md-row">
                                <ul className="nav mr-auto d-none d-md-flex">
                                    <li><a href="#" className="nav-link px-2"> <i className="fab fa-facebook"></i> </a></li>
                                    <li><a href="#" className="nav-link px-2"> <i className="fab fa-instagram"></i> </a>
                                    </li>
                                    <li><a href="#" className="nav-link px-2"> <i className="fab fa-twitter"></i> </a></li>
                                </ul>
                                <ul className="nav">
                                    <li className="nav-item"><a href="#" className="nav-link"> Delivery </a></li>
                                    <li className="nav-item"><a href="#" className="nav-link"> Help </a></li>
                                    <li className="nav-item dropdown"><a href="#" className="nav-link dropdown-toggle"
                                                                         data-toggle="dropdown"> USD </a>
                                        <ul className="dropdown-menu dropdown-menu-right">
                                            <li><a className="dropdown-item" href="#">EUR</a></li>
                                            <li><a className="dropdown-item" href="#">GBP</a></li>
                                        </ul>
                                    </li>
                                </ul>

                            </nav>
                        </div>
                    </section>

                    <section className="border-bottom">
                        <nav className="navbar navbar-main navbar-expand-lg navbar-light">
                            <div className="container">
                                <a className="navbar-brand" href="http://bootstrap-ecommerce.com"><img src="images/logo.png" className="logo"/></a>

                                {!this.props.confirmation && <a href={"/cart"} className="btn btn-light d-md-none">My cart ({this.cartQuantity(this.state.productItems)})</a>}

                                <button className="navbar-toggler" type="button" data-toggle="collapse"
                                        data-target="#main_nav1" aria-controls="main_nav" aria-expanded="false"
                                        aria-label="Toggle navigation">
                                    <span className="navbar-toggler-icon"/>
                                </button>

                                <div className="collapse navbar-collapse" id="main_nav1">
                                    <ul className="navbar-nav mr-auto">
                                        <li className="nav-item">
                                            <a className="nav-link" href="/">Home </a>
                                        </li>
                                        <li className="nav-item dropdown">
                                            <a className="nav-link dropdown-toggle" data-toggle="dropdown"
                                               href={"/products"}>Shop</a>
                                            <div className="dropdown-menu">
                                                {Object.entries(this.state.categories).map((categoryName, index) =>{

                                                   return( <ul className="dropdown-item" key={index}>
                                                        <a className="dropdown-item" href={'/products?category='+categoryName[0]}>{categoryName[0]}</a>
                                                           {this.state.categories[categoryName[0]].map((productGroup, index) =>{
                                                                 return( <li className="dropdown-item" key={index}>
                                                                    <a className="dropdown-item" href={'/products?group=' +productGroup.name}>{productGroup.name}</a>
                                                                 </li>
                                                           )} )}
                                                    </ul>
                                               ) }) }
                                            </div>

                                        </li>
                                        <li className="nav-item">
                                            <a className="nav-link" href="#">About Us</a>
                                        </li>
                                        <li className="nav-item">
                                            <a className="nav-link" href="#">Contact Us</a>
                                        </li>
                                    </ul>

                                    {!this.props.confirmation &&
                                    <div className="widgets-wrap d-none d-md-block">
                                        <a href={"/cart"} className="widget-header">
                                            <div className="icon">
                                                <i className="icon-sm rounded-circle border fa fa-shopping-cart" />
                                                <span className="notify">{this.cartQuantity(this.state.productItems)}</span>
                                            </div>
                                        </a>
                                    </div>
                                }
                                </div>


                            </div>

                        </nav>
                    </section>

                </header>

        )
    }
}
export default Header;