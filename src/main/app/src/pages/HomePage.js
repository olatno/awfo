import React, {Component} from "react";
import Header from "../structure/Header";
import Banner from "../structure/Banner";
import Footer from "../structure/Footer";
import ReactDOM from "react-dom";
import axios from "axios";
import {weightConverter} from "../helpers/Utils";

class HomePage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            popularItems:[],
            popularGroups:[],
            categories:[]
        }
    }

    componentDidMount() {

        const POPULAR_REST_API_URL = '/awf/ecommerce/rest/categoriesAndGroups';
        const fetchPopularProduct = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(POPULAR_REST_API_URL)
                this.setState({popularItems: res.data});
                this.setState({popularGroups: res.data.productGroups});
                this.setState({categories: res.data.categories});
            }catch (err){
                console.log(err)
            }

            this.setState({loading:false})
        }
        fetchPopularProduct().then()
    }

    render() {

        return(
            <div>
                <Header/>
                <Banner/>
                <section className="section-name padding-y-sm">
                    <div className="container">

                        <header className="section-heading">
                            {/*<a href="#" className="btn btn-outline-primary float-right">See all</a>*/}
                            <h3 className="section-title">Popular categories</h3>
                        </header>

                        <div className="row">
                            { this.state.categories.map((category, index) => {
                                return(
                                    <div className="col-md-3" key={index}>
                                        <div href="#" className="card card-product-grid">
                                            <a href={"/products"+"?category="+category.name} className="img-wrap"> <img src={category.gallery.images[0].path}/> </a>
                                            <figcaption className="info-wrap">
                                                <a href={"/products"+"?category="+category.name} className="title">{category.name}</a>
                                                {/*<div className="price mt-1">${productItem.productWeight.weightList[0].price.value} / <span>{weightConverter(productItem.productWeight.weightList[0].value)}</span></div>*/}
                                            </figcaption>
                                        </div>
                                    </div>
                                )
                            })}
                        </div>

                        <hr/>


                        <header className="section-heading">
                            {/*<a href="#" className="btn btn-outline-primary float-right">See all</a>*/}
                            <h3 className="section-title">Popular product groups</h3>
                        </header>

                        <div className="row">
                            { this.state.popularGroups.map((popularGroup, index) => {
                                return(
                                    <div className="col-md-3" key={index}>
                                        <div href="#" className="card card-product-grid">
                                            <a href={"/products"+"?group="+popularGroup.name} className="img-wrap"> <img src={popularGroup.gallery.images[0].path}/> </a>
                                            <figcaption className="info-wrap">
                                                <a href={"/products"+"?group="+popularGroup.name} className="title">{popularGroup.name}</a>
                                                {/*<div className="price mt-1">${productItem.productWeight.weightList[0].price.value} / <span>{weightConverter(productItem.productWeight.weightList[0].value)}</span></div>*/}
                                            </figcaption>
                                        </div>
                                    </div>
                                )
                            })}
                        </div>

                    </div>
                </section>
                <Footer/>
            </div>
        )
    }
}
export default HomePage;