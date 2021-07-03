import React, {Component} from "react";
import Header from "../structure/Header";
import BannerSmall from "../structure/BannerSmall";
import Footer from "../structure/Footer";
import { loadStripe } from "@stripe/stripe-js";
import { Elements } from "@stripe/react-stripe-js";
import CheckoutForms from "../ecommerce/CheckoutForms";
import "../helpers/PaymentPage.css";
//import '../helpers/CardSectionStyles.css'
import ReactDOM from "react-dom";
import axios from "axios";
import Input from "../widgets/Input";
import Button from "../widgets/Button";
import {weightConverter} from "../helpers/Utils";
import CheckoutForm from "../ecommerce/CheckoutForm";
const promise = loadStripe("pk_test_51IcxYeC2cElQs07KF3jBo0L4ZLctyuDyM3iJsVQzA7UMS9q2AcY6xJyPMRPuZy6b5mFeJHZkpZQFX1IYoQOrFF3h00ryFoDYD8");
class PaymentPage extends Component{

    constructor(props) {
        super(props);
        this.state = {

                        city:'',
                        country:'',
                        line1:'',
                        line2:'',
                        postal_code:'',
                        state:'',
                        cityDel:'',
                        countryDel:'',
                        line1Del:'',
                        line2Del:'',
                        postal_codeDel:'',
                        stateDel:'',
                        name:'',
                        phone:'',
                        email:'',
                        description:'',
                        firstName:'',
                        lastName:'',
                        firstNameDel:'',
                        lastNameDel:'',
                        phoneDel:'',
                        billing:true,
                        delivery:false,
                        checked:false,
                        cartItems:[],
                        clientSecrete:'',
                        paymentIntentId:'',
                        continuePayment:false,
                        processing:false
        }
         this.handleSubmit = this.handleSubmit.bind(this);
        this.handleRadioChange = this.handleRadioChange.bind(this);
    }

    handleSubmit(event){
        let address = { billing: {
                city: this.state.city,
                country: this.state.country,
                lineOne: this.state.line1,
                lineTwo: this.state.line2,
                postalCode: this.state.postal_code,
                state: this.state.state,
                name: this.state.firstName +' '+ this.state.lastName,
                phone:this.state.phone,
                email:this.state.email,
            },
            delivery: {
                city: this.state.cityDel ==='' ? this.state.city : this.state.cityDel,
                country: this.state.countryDel === '' ? this.state.country : this.state.countryDel,
                lineOne: this.state.line1Del === '' ? this.state.line1 : this.state.line1Del,
                lineTwo: this.state.line2Del === '' ? this.state.line2 :  this.state.line2Del,
                postalCode: this.state.postal_codeDel === '' ?this.state.postal_code : this.state.postal_codeDel,
                state: this.state.stateDel === '' ?this.state.state:this.state.stateDel,
                name: (this.state.firstNameDel ==='' ? this.state.firstName : this.state.firstNameDel)  +' '+ (this.state.lastNameDel === '' ? this.state.lastName : this.state.lastNameDel),
                phone:this.state.phoneDel === '' ? this.state.phone : this.state.phoneDel
            }

        }
        this.setState({name: address.billing.name})
        let formData = {
                        address : address,
                        description:'test'
                        }

        const STRIPE_CUSTOMER_REST_API_URL = '/awf/ecommerce/rest/createCustomer';
        const createStripeCustomer = async (cartUrl, value) => {
            this.setState({processing:true})
            try {
                const res = await axios.post(cartUrl, value)
                if(res.data.clientSecret !==  '' && res.data.paymentIntentId !== '') {
                    this.setState({clientSecrete: res.data.clientSecret});
                    this.setState({paymentIntentId: res.data.paymentIntentId});
                }
                this.resetFormFields()
            }catch (err){
                console.log(err)
            }
        }
        createStripeCustomer(STRIPE_CUSTOMER_REST_API_URL, formData).then()
        console.log(formData)
        event.preventDefault();

    }

    resetFormFields(){
        this.setState({postal_code: ""});
        this.setState({country: ""});
        this.setState({state: ""});
        this.setState({city: ""});
        this.setState({line1: ""});
        this.setState({line2: ""});
        this.setState({postal_codeDel: ""});
        this.setState({countryDel: ""});
        this.setState({stateDel: ""});
        this.setState({cityDel: ""});
        this.setState({line1Del: ""});
        this.setState({line2Del: ""});
        this.setState({lastName: ""});
        this.setState({firstName: ""});
        this.setState({phone: ""});
        this.setState({email: ""});
        this.setState({lastNameDel: ""});
        this.setState({firstNameDel: ""});
        this.setState({phoneDel: ""});
        this.setState({continuePayment:true})
        this.setState({processing:false})
    }

    handleRadioChange(event){
        let checkedValue = event.target.value
        this.setState({delivery : checkedValue === 'delivery'})
        this.setState({billing : checkedValue === 'billing'})
    }
    componentDidMount() {
        const REVIEW_REST_API_URL = '/awf/ecommerce/rest/cartReview';
        const cartItems = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(REVIEW_REST_API_URL)
                if(res.data.productItems !== null) {
                    this.setState({cartItems: res.data.productItems});
                }
            }catch (err){
                console.log(err)
            }
            this.setState({loading:false})
        }
        cartItems().then()
    }
    productItemTotalPrice = (productItem) => {
        return productItem.quantity * productItem.product.productWeight.weightList[productItem.weightListIndex].price.value
    }

    render() {
        return(
            <div>
                <Header/>
                <BannerSmall/>

                <section className="section-content padding-y ">
                    <div className="container">

                        <div className="row">
                            <main className="col-md-8">

                                <article className="card mb-4">
                                    <div className="card-body">
                                        <h4 className="card-title mb-4">Review cart</h4>
                                        <div className="row">
                                            {
                                                this.state.cartItems.map((productItem, index) => {
                                                    return (
                                                        <div className="col-md-6" key={index}>
                                                            <figure className="itemside  mb-4">
                                                                <div className="aside"><img
                                                                    src={productItem.product.gallery.images[0].path}
                                                                    className="border img-sm"/></div>
                                                                <figcaption className="info">
                                                                    <p>{productItem.product.name} - {weightConverter(productItem.product.productWeight.weightList[productItem.weightListIndex].value)} </p>
                                                                    <span
                                                                        className="text-muted">{productItem.quantity} x ${productItem.product.productWeight.weightList[productItem.weightListIndex].price.value} = ${this.productItemTotalPrice(productItem)} </span>
                                                                </figcaption>
                                                            </figure>
                                                        </div>
                                                    )

                                                })

                                            }
                                        </div>

                                    </div>

                                </article>

                                <form action="" onSubmit={this.handleSubmit}>

                                {/*<article className="card mb-4">*/}
                                {/*    <div className="card-body">*/}
                                {/*        <h4 className="card-title mb-4">Contact info</h4>*/}
                                {/*            <div className="row">*/}

                                {/*                <Input type={'text'}*/}
                                {/*                       class={'form-group col-sm-6'}*/}
                                {/*                       className={'form-control'}*/}
                                {/*                       title= {'First name'}*/}
                                {/*                       name= {'firstName'}*/}
                                {/*                       value={this.state.firstName}*/}
                                {/*                       placeholder = {'Enter first name'}*/}
                                {/*                       handleChange = {(event) =>{this.setState({firstName: event.target.value})}}*/}
                                {/*                />*/}
                                {/*                <Input type={'text'}*/}
                                {/*                       class={'form-group col-sm-6'}*/}
                                {/*                       className={'form-control'}*/}
                                {/*                       title= {'Last name'}*/}
                                {/*                       name= {'lastName'}*/}
                                {/*                       value={this.state.lastName}*/}
                                {/*                       placeholder = {'Enter last name'}*/}
                                {/*                       handleChange = {(event) =>{this.setState({lastName: event.target.value})}}*/}
                                {/*                />*/}
                                {/*                <Input type={'text'}*/}
                                {/*                       class={'form-group col-sm-6'}*/}
                                {/*                       className={'form-control'}*/}
                                {/*                       title= {'Phone'}*/}
                                {/*                       name= {'phone'}*/}
                                {/*                       value={this.state.phone}*/}
                                {/*                       placeholder = {'Enter phone number'}*/}
                                {/*                       handleChange = {(event) =>{this.setState({phone: event.target.value})}}*/}
                                {/*                />*/}
                                {/*                <Input type={'text'}*/}
                                {/*                       class={'form-group col-sm-6'}*/}
                                {/*                       className={'form-control'}*/}
                                {/*                       title= {'Email'}*/}
                                {/*                       name= {'email'}*/}
                                {/*                       value={this.state.email}*/}
                                {/*                       placeholder = {'example@gmail.com'}*/}
                                {/*                       handleChange = {(event) =>{this.setState({email: event.target.value})}}*/}
                                {/*                />*/}
                                {/*           </div>*/}

                                {/*    </div>*/}

                                {/*</article>*/}


                                <article className="card mb-4">
                                    <div className="card-body">
                                        <h4 className="card-title mb-4">Delivery info</h4>
                                            <div className="row">
                                                <div className="form-group col-sm-6">
                                                    <label className="js-check box active">
                                                        <input type="radio" name="address" value="billing" checked={this.state.billing === true ? "checked" : ""} onChange={this.handleRadioChange}/>

                                                            <h6 className="title">Billing Address</h6>
                                                            <p className="text-muted">{""}</p>
                                                    </label>
                                                </div>

                                                <div className="form-group col-sm-6">
                                                    <label className="js-check box">
                                                        <input type="radio" name="address" value="delivery" checked={this.state.delivery === true ? "checked" : ""} onChange={this.handleRadioChange}/>
                                                            <h6 className="title">Delivery Address</h6>
                                                            <p className="text-muted">If different from Billing address </p>
                                                    </label>
                                                </div>
                                            </div>

                                            <div className="billing" style={{display: this.state.billing === true ? "block" : "none"}}>

                                                <div className="row">
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'First name'}
                                                           name= {'firstName'}
                                                           value={this.state.firstName}
                                                           placeholder = {'Enter first name'}
                                                           handleChange = {(event) =>{this.setState({firstName: event.target.value})}}
                                                    />
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'Last name'}
                                                           name= {'lastName'}
                                                           value={this.state.lastName}
                                                           placeholder = {'Enter last name'}
                                                           handleChange = {(event) =>{this.setState({lastName: event.target.value})}}
                                                    />
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'Phone'}
                                                           name= {'phone'}
                                                           value={this.state.phone}
                                                           placeholder = {'Enter phone number'}
                                                           handleChange = {(event) =>{this.setState({phone: event.target.value})}}
                                                    />
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'Email'}
                                                           name= {'email'}
                                                           value={this.state.email}
                                                           placeholder = {'example@gmail.com'}
                                                           handleChange = {(event) =>{this.setState({email: event.target.value})}}
                                                    />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-12'}
                                                       className={'form-control'}
                                                       title= {'Address line 1'}
                                                       name= {'line1'}
                                                       value={this.state.line1}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({line1: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-12'}
                                                       className={'form-control'}
                                                       title= {'Address line 2'}
                                                       name= {'line2'}
                                                       value={this.state.line2}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({line2: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Town*'}
                                                       name= {'city'}
                                                       value={this.state.city}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({city: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'County*'}
                                                       name= {'state'}
                                                       value={this.state.state}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({state: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Postal code*'}
                                                       name= {'postal_code'}
                                                       value={this.state.postal_code}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({postal_code: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Country*'}
                                                       name= {'country'}
                                                       value={this.state.country}
                                                       placeholder = {''}
                                                       handleChange = {(event) => {this.setState({country: event.target.value})}}
                                                />
                                                </div>
                                            </div>
                                            <div className="delivery" style={{display: this.state.delivery === true ? "block" : "none"}}>
                                                <div className="row">
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'First name'}
                                                           name= {'firstName'}
                                                           value={this.state.firstNameDel}
                                                           placeholder = {'Enter first name'}
                                                           handleChange = {(event) =>{this.setState({firstNameDel: event.target.value})}}
                                                    />
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'Last name'}
                                                           name= {'lastName'}
                                                           value={this.state.lastNameDel}
                                                           placeholder = {'Enter last name'}
                                                           handleChange = {(event) =>{this.setState({lastNameDel: event.target.value})}}
                                                    />
                                                    <Input type={'text'}
                                                           class={'form-group col-sm-6'}
                                                           className={'form-control'}
                                                           title= {'Phone'}
                                                           name= {'phone'}
                                                           value={this.state.phoneDel}
                                                           placeholder = {'Enter phone number'}
                                                           handleChange = {(event) =>{this.setState({phoneDel: event.target.value})}}
                                                    />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-12'}
                                                       className={'form-control'}
                                                       title= {'Address line 1'}
                                                       name= {'line1'}
                                                       value={this.state.line1Del}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({line1Del: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-12'}
                                                       className={'form-control'}
                                                       title= {'Address line 2'}
                                                       name= {'line2'}
                                                       value={this.state.line2Del}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({line2Del: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Town*'}
                                                       name= {'city'}
                                                       value={this.state.cityDel}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({cityDel: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'County*'}
                                                       name= {'state'}
                                                       value={this.state.stateDel}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({stateDel: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Postal code*'}
                                                       name= {'postal_code'}
                                                       value={this.state.postal_codeDel}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({postal_codeDel: event.target.value})}}
                                                />
                                                <Input type={'text'}
                                                       class={'form-group col-sm-6'}
                                                       className={'form-control'}
                                                       title= {'Country*'}
                                                       name= {'country'}
                                                       value={this.state.countryDel}
                                                       placeholder = {''}
                                                       handleChange = {(event) =>{this.setState({countryDel: event.target.value})}}
                                                />
                                            </div>
                                            </div>
                                             <div className="row">
                                            < Button
                                                class={'form-group col-sm-8'}
                                                className={'btn btn-primary btn-block'}
                                                type={'submit'}
                                                value={true}
                                                title={ <span id="button-text">
                                                        {this.state.processing ? (
                                                            <div className="loader"/>
                                                        ) : (
                                                            "Continue to payment"
                                                        )}
                                                        </span>}
                                                action={''}
                                                />
                                            </div>
                                            </div>

                                </article>
                                </form>
                                {this.state.continuePayment &&
                                <article className="accordion" id="accordion_pay">
                                    <div className="card">
                                        <header className="card-header">
                                            <img src="/images/misc/payment-paypal.png" className="float-right"
                                                 height="24"/>
                                            <label className="form-check collapsed" data-toggle="collapse"
                                                   data-target="#pay_paynet">
                                                <input className="form-check-input" name="payment-option" checked
                                                       type="radio" value="option2" readOnly/>
                                                <h6 className="form-check-label">
                                                    Paypal
                                                </h6>
                                            </label>
                                        </header>
                                        <div id="pay_paynet" className="collapse show" data-parent="#accordion_pay">
                                            <div className="card-body">
                                                <p className="text-center text-muted">Connect your PayPal account and
                                                    use it to pay your bills. You'll be redirected to PayPal to add your
                                                    billing information.</p>
                                                <p className="text-center">
                                                    <a href="#"><img src={"/images/misc/btn-paypal.png"}
                                                                     height="32" alt={'paypal'}/></a>
                                                    <br/><br/>
                                                </p>
                                            </div>

                                        </div>

                                    </div>

                                    {/*<div className="card">*/}
                                    {/*    <header className="card-header">*/}
                                    {/*        <img src="/images/misc/payment-card.png" className="float-right"*/}
                                    {/*             height="24"/>*/}
                                    {/*            <label className="form-check" data-toggle="collapse"*/}
                                    {/*                   data-target="#pay_payme">*/}
                                    {/*                <input className="form-check-input" name="payment-option"*/}
                                    {/*                       type="radio" value="option2" readOnly/>*/}
                                    {/*                    <h6 className="form-check-label"> Credit Card </h6>*/}
                                    {/*            </label>*/}
                                    {/*    </header>*/}
                                    {/*    <div id="pay_payme" className="collapse" data-parent="#accordion_pay">*/}
                                    {/*        <div className="card-body">*/}
                                    {/*            <p className="alert alert-success">Some information or instruction</p>*/}
                                    {/*            <form className="form-inline">*/}
                                    {/*                <input type="text" className="form-control mr-2"*/}
                                    {/*                       placeholder="xxxx-xxxx-xxxx-xxxx" name="" readOnly/>*/}
                                    {/*                    <input type="text" className="form-control mr-2" style={{width: "100px"}}*/}
                                    {/*                            placeholder="dd/yy" name="" readOnly/>*/}
                                    {/*                        <input type="number" maxLength="3"*/}
                                    {/*                               className="form-control mr-2" style={{width: "100px"}}*/}
                                    {/*                               placeholder="cvc" name="" readOnly/>*/}
                                    {/*                            <button className="btn btn btn-success">Button</button>*/}
                                    {/*            </form>*/}
                                    {/*        </div>*/}

                                    {/*    </div>*/}

                                    {/*</div>*/}
                                    {this.state.clientSecrete !== '' &&
                                    <div className="card">
                                        <header className="card-header">
                                            <img src="/images/misc/payment-card.png" className="float-right"
                                                 height="24"/>
                                            <label className="form-check" data-toggle="collapse"
                                                   data-target="#pay_payme">
                                                <input className="form-check-input" name="payment-option"
                                                       type="radio" value="option2" readOnly/>
                                                <h6 className="form-check-label"> Stripe Credit Card </h6>
                                            </label>
                                        </header>

                                        <div id="pay_payme" className="collapse" data-parent="#accordion_pay">
                                            <div className="card-body">
                                                <p className="alert alert-success">Some information or instruction</p>
                                                {
                                                    <div className="App">
                                                        <Elements stripe={promise}>
                                                            <CheckoutForm secreteValue={this.state.clientSecrete}
                                                                           paymentId={this.state.paymentIntentId}
                                                                            name={this.state.name}/>
                                                        </Elements>
                                                    </div>
                                                }
                                            </div>

                                        </div>

                                    </div>
                                    }

                                    <div className="card">
                                        <header className="card-header">
                                            <img src="/images/misc/payment-bank.png" className="float-right"
                                                 height="24"/>
                                            <label className="form-check" data-toggle="collapse"
                                                   data-target="#pay_card">
                                                <input className="form-check-input" name="payment-option"
                                                       type="radio" value="option1" readOnly/>
                                                <h6 className="form-check-label"> Bank Transfer </h6>
                                            </label>
                                        </header>
                                        <div id="pay_card" className="collapse" data-parent="#accordion_pay">
                                            <div className="card-body">
                                                <p className="text-muted">Some instructions about how to pay </p>
                                                <p>
                                                    Bank of America, Account number: 12345678912346 <br/>
                                                    IBAN: 12345, SWIFT: 987654
                                                </p>
                                            </div>

                                        </div>

                                    </div>

                                </article>
                                }
                            </main>

                            <aside className="col-md-4">
                                <div className="card shadow">
                                    <div className="card-body">
                                        <h4 className="mb-3">Overview</h4>
                                        <dl className="dlist-align">
                                            <dt className="text-muted">Delivery:</dt>
                                            <dd>Pick-up</dd>
                                        </dl>
                                        <dl className="dlist-align">
                                            <dt className="text-muted">Delivery type:</dt>
                                            <dd>Standart</dd>
                                        </dl>
                                        <dl className="dlist-align">
                                            <dt className="text-muted">Payment method:</dt>
                                            <dd>Cash</dd>
                                        </dl>
                                        <hr/>
                                            <dl className="dlist-align">
                                                <dt>Total:</dt>
                                                <dd className="h5">$300.50</dd>
                                            </dl>
                                            <hr/>
                                                <p className="small mb-3 text-muted">By clicking you are agree with terms of condition </p>
                                                <a href="#" className="btn btn-primary btn-block"> Button </a>

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
export default PaymentPage;