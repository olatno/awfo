import React, {Component} from "react";
import axios from "axios";

class Footer extends Component {
    constructor(props) {
        super(props);
        this.state = {
            brandItems:[]
        }
    }
    componentDidMount() {

        const BRAND_REST_API_URL = '/awf/ecommerce/rest/brands';
        const fetchBrand = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(BRAND_REST_API_URL)
                this.setState({brandItems: res.data});
            }catch (err){
                console.log(err)
            }

            this.setState({loading:false})
        }
        fetchBrand().then()
    }

    render() {
        return(
            <footer className="section-footer border-top">
                <div className="container">
                    <section className="footer-top padding-y">
                        <div className="row">
                            <aside className="col-md col-6">
                                <h6 className="title">Brands</h6>
                                <ul className="list-unstyled">
                                    {this.state.brandItems.map((brand, index) => {
                                        return(
                                        <li key={index}><a href="#">{brand.name}</a></li>
                                        )
                                    })}

                                </ul>
                            </aside>
                            <aside className="col-md col-6">
                                <h6 className="title">Company</h6>
                                <ul className="list-unstyled">
                                    <li><a href="#">About us</a></li>
                                    <li><a href="#">Career</a></li>
                                    <li><a href="#">Find a store</a></li>
                                    <li><a href="#">Rules and terms</a></li>
                                    <li><a href="#">Sitemap</a></li>
                                </ul>
                            </aside>
                            <aside className="col-md col-6">
                                <h6 className="title">Help</h6>
                                <ul className="list-unstyled">
                                    <li><a href="#">Contact us</a></li>
                                    <li><a href="#">Money refund</a></li>
                                    <li><a href="#">Order status</a></li>
                                    <li><a href="#">Shipping info</a></li>
                                    <li><a href="#">Open dispute</a></li>
                                </ul>
                            </aside>
                            <aside className="col-md col-6">
                                <h6 className="title">Account</h6>
                                <ul className="list-unstyled">
                                    <li><a href="#"> User Login </a></li>
                                    <li><a href="#"> User register </a></li>
                                    <li><a href="#"> Account Setting </a></li>
                                    <li><a href="#"> My Orders </a></li>
                                </ul>
                            </aside>
                            <aside className="col-md">
                                <h6 className="title">Social</h6>
                                <ul className="list-unstyled">
                                    <li><a href="#"> <i className="fab fa-facebook"></i> Facebook </a></li>
                                    <li><a href="#"> <i className="fab fa-twitter"></i> Twitter </a></li>
                                    <li><a href="#"> <i className="fab fa-instagram"></i> Instagram </a></li>
                                    <li><a href="#"> <i className="fab fa-youtube"></i> Youtube </a></li>
                                </ul>
                            </aside>
                        </div>

                    </section>

                    <section className="footer-bottom border-top row">
                        <div className="col-md-2">
                            <p className="text-muted"> &copy 2019 Company name </p>
                        </div>
                        <div className="col-md-8 text-md-center">
                            <span className="px-2">info@pixsellz.io</span>
                            <span className="px-2">+879-332-9375</span>
                            <span className="px-2">Street name 123, Avanue abc</span>
                        </div>
                        <div className="col-md-2 text-md-right text-muted">
                            <i className="fab fa-lg fa-cc-visa"></i>
                            <i className="fab fa-lg fa-cc-paypal"></i>
                            <i className="fab fa-lg fa-cc-mastercard"></i>
                        </div>
                    </section>
                </div>
            </footer>
        )
    }
}
export default Footer;