import React, {Component} from "react";
import Header from "../structure/Header";
import Banner from "../structure/Banner";
import Footer from "../structure/Footer";
import ReactDOM from "react-dom";
import BannerSmall from "../structure/BannerSmall";
import axios from "axios";
import {weightConverter} from "../helpers/Utils";

class CartPage extends Component{

    constructor(props) {
        super(props);
        this.state = {
            productItems:[],
            disableMinus:false,
            loading:false,
            cartQuantities:0,
            cartTotal:0,
            totalWeight:0
        }

    }
    // static contextType = CartQuantities

    componentDidMount() {
        const { quantities, setQuantities } = this.context
        const CARTS_REST_API_URL = '/awf/ecommerce/rest/productItems';
        const fetchCarts = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(CARTS_REST_API_URL)
                this.setState({productItems: res.data});
                this.setState({cartQuantities:this.cartQuantity(this.state.productItems)})
                this.setState({cartTotal:this.cartTotalPrice(this.state.productItems)})
                this.setState({totalWeight: this.cartTotalWeight(this.state.productItems)})
                // setQuantities(this.cartQuantity(this.state.productItems))
                console.log(quantities)
            }catch (err){
                console.log(err)
            }

            this.setState({loading:false})
        }
        fetchCarts().then()
    }

    handleUpdateQuantity = (event) =>{
        let quantity = event.target.id === 'button-plus' ? 1 : -1
        let id = event.currentTarget.dataset.productitemId
        let updateQuantity = {
            quantity:quantity,
            id:id
        }
        const UPDATE_PRODUCT_ITEM_REST_API_URL = '/awf/ecommerce/rest/updateProductItem';
        const updateProductItem = async (cartUrl, value) => {
            try {
                await axios.post(cartUrl, value)
                window.location.href = "/cart";
            }catch (err){
                console.log(err)
            }
        }
        updateProductItem(UPDATE_PRODUCT_ITEM_REST_API_URL, updateQuantity).then()
    }

    handleRemoveProductItem = (event) =>{
        let id = event.currentTarget.dataset.productitemId
        let productItem = {
            id:id
        }
        const DELETE_PRODUCT_ITEM_REST_API_URL = '/awf/ecommerce/rest/deleteProductItem';
        const deleteProductItem = async(restUrl, itemId) =>{
            try {
                await axios.post(restUrl, itemId)
                window.location.href = "/cart";
            }catch (err){
                console.log(err)
            }
        }
        deleteProductItem(DELETE_PRODUCT_ITEM_REST_API_URL, productItem).then()
    }
    cartQuantity = (productItems) => {
        let total = 0
        productItems.map((productItem, index) => {
            total+=productItem.quantity
        })
        return total
    }

    cartTotalPrice = (productItems) => {
        let totalPrice = 0;
        productItems.map((productItem, index) => {
            totalPrice+=(productItem.quantity * productItem.product.productWeight.weightList[productItem.weightListIndex].price.value)
        })
        return totalPrice;
    }

    cartTotalWeight = (productItems) => {
        let totalWeight = 0;
        productItems.map((productItem, index) => {
            totalWeight += (productItem.quantity * productItem.product.productWeight.weightList[productItem.weightListIndex].value)
        })
        return weightConverter(totalWeight);
    }

    render() {

        return(
            <div>
                <Header cartQty={this.state.cartQuantities}/>
                <BannerSmall/>
                <section className="section-content padding-y">
                <div className="container">
                <div className="row">
                    <aside className="col-lg-9">
                        <div className="card">
                            <table className="table table-borderless table-shopping-cart">
                                <thead className="text-muted">
                                <tr className="small text-uppercase">
                                    <th scope="col">Product</th>
                                    <th scope="col" width="120">Quantity</th>
                                    <th scope="col" width="120">Price</th>
                                    <th scope="col" className="text-right" width="200"></th>
                                </tr>
                                </thead>
                                <tbody>
                                {   this.state.productItems.map((productItem, index) => {
                                return(
                                <tr key={index} data-id={productItem.id}>
                                    <td>
                                        <figure className="itemside align-items-center">
                                            <div className="aside"><img src={productItem.product.gallery.images[0].path} className="img-sm" />
                                            </div>
                                            <figcaption className="info">
                                                <a href="#" className="title text-dark">{productItem.product.name}</a>
                                                <p className="text-muted small">Matrix: {weightConverter(productItem.product.productWeight.weightList[productItem.weightListIndex].value)}<br/> Brand: {productItem.product.productCode.brand.name}</p>
                                            </figcaption>
                                        </figure>
                                    </td>
                                    <td>
                                        <div className="input-group mb-3 input-spinner">
                                            <div className="input-group-append">
                                                <button className="btn btn-light" disabled={productItem.quantity <= 1} type="button" id="button-minus" data-productitem-id={productItem.id} onClick={(e)=>{this.handleUpdateQuantity(e)}}> &minus; </button>
                                            </div>
                                            <input type="text" className="form-control" value={productItem.quantity} readOnly/>
                                            <div className="input-group-prepend">
                                                <button className="btn btn-light" type="button" id="button-plus" data-productitem-id={productItem.id} onClick={(e)=>{this.handleUpdateQuantity(e)}}> +</button>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <div className="price-wrap">
                                            <var className="price">${productItem.product.productWeight.weightList[productItem.weightListIndex].price.value * productItem.quantity}</var>
                                            <small className="text-muted"> ${productItem.product.productWeight.weightList[productItem.weightListIndex].price.value} each </small>
                                        </div>

                                    </td>
                                    <td className="text-right">
                                        <button className="btn btn-light" data-productitem-id={productItem.id} onClick={(e)=>{this.handleRemoveProductItem(e)}}> Remove</button>
                                    </td>
                                </tr>
                                )
                                })}
                                </tbody>
                            </table>

                            <div className="card-body border-top">
                                <p className="icontext"><i className="icon text-success fa fa-truck"></i> Total weight For Delivery
                                    is {this.state.totalWeight}</p>
                            </div>

                        </div>

                    </aside>

                    <aside className="col-lg-3">

                        <div className="card mb-3">
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Have coupon?</label>
                                        <div className="input-group">
                                            <input type="text" className="form-control" name=""
                                                   placeholder="Coupon code" />
			<span className="input-group-append">
				<button className="btn btn-primary">Apply</button>
			</span>
                                        </div>
                                    </div>
                                </form>
                            </div>

                        </div>


                        <div className="card">
                            <div className="card-body">
                                <dl className="dlist-align">
                                    <dt>Total price:</dt>
                                    <dd className="text-right">${this.state.cartTotal}</dd>
                                </dl>
                                <dl className="dlist-align">
                                    <dt>Discount:</dt>
                                    <dd className="text-right text-danger">- $10.00</dd>
                                </dl>
                                <dl className="dlist-align">
                                    <dt>Total:</dt>
                                    <dd className="text-right text-dark b"><strong>${this.state.cartTotal}</strong></dd>
                                </dl>
                                <hr/>
                                    <p className="text-center mb-3">
                                        <img src="/images/misc/payments.png" height="26"/>
                                    </p>
                                    <a href="/payment" className="btn btn-primary btn-block"> Make Purchase </a>
                                    <a href="/products" className="btn btn-light btn-block">Continue Shopping</a>
                            </div>

                        </div>


                    </aside>



                </div>
                </div>
                </section>
                <Footer/>
            </div>
        )
    }
}
export default CartPage;