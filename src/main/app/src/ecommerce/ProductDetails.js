import React, {useState} from 'react';
import axios from "axios";
import {weightConverter} from '../helpers/Utils.js'
const ProductDetails = (props) => {

    const [quantity, setAddQuantity] = useState(1);
    const [minus, setDisableMinus] = useState(true);
    const [price, setChangePrice] = useState(0);
    const [weightIndex, setWeightIndex] = useState('');
    const [imageIndex, setImageIndex] = useState(0);
    const { productInfo } = props;

    const handleClick = () => {
        let defaultWeight = props.productsFilter
        let weightIndexValue = weightIndex !== '' ? weightIndex : defaultWeight
        let cookieValue = document.cookie.replace(/(?:(?:^|.*;\s*)CartID\s*\=\s*([^;]*).*$)|^.*$/, "$1");
        let productDetails = {
            product:productInfo,
            quantity:quantity,
            weightListIndex:weightIndexValue
        }
        const CART_REST_API_URL = '/awf/ecommerce/rest/cart';
        const updateCart = async (cartUrl, value) => {
            try {
                const res = await axios.post(cartUrl, value)
                if(!cookieValue){
                    setCookie("CartID", res.data.cookie, 3)
                }
                window.location.href = "/cart";
            }catch (err){
                console.log(err)
            }
        }
        updateCart(CART_REST_API_URL, productDetails).then()
        props.productDetailsToggle();
    }

    const setCookie = (name,value, days) =>{

        let date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        let expires = "; expires=" + date.toUTCString();
        document.cookie = name + "=" + (value || "")  + expires + "; path=/";
    }

    const handleUpdateQuantity = (event) =>{
        let updateQuantity = quantity
        event.target.id === 'button-plus' ? updateQuantity++ : updateQuantity--
        setAddQuantity(updateQuantity)

        updateQuantity > 1 ? setDisableMinus(false) : setDisableMinus(true)
    }

    const handleCloseDialog = () =>{
        props.productDetailsToggle();
    }

    const handleChangeSelect = (event) => {
        let index =  event.target.value;
        let weightPrice = productInfo.productWeight.weightList[index].price.value
        let productsFilter = props.productsFilter
        console.log(productsFilter['price'])
        console.log(productsFilter['productWeight'])
        setChangePrice(weightPrice);
        setWeightIndex(index);
    }

    const handleImageClick = (event) => {
        let index =  event.currentTarget.getAttribute('value');
        setImageIndex(parseInt(index))
    }



    return (

        <div className="modal" tabIndex="-1" role="dialog" aria-hidden="true" style={{overflow: 'auto'}}>
            <div className="modal-dialog modal-lg modal-sm" role="document">
                <div className="modal-content">
                <button type="button" className="close" data-dismiss="modal" aria-label="Close" onClick={(e)=>{handleCloseDialog(e)}}>&times;</button>
                <div className="card">
                    <div className="row no-gutters">
                        <aside className="col-sm-6 border-right">
                            <article className="gallery-wrap">
                                <div className="img-big-wrap">
                                    <a href="#"><img src={ productInfo.gallery.images[imageIndex].path} /></a>
                                </div>
                                <div className="thumbs-wrap">
                                    {   productInfo.gallery.images.map((image, index) => {
                                        return (
                                            <button type={"button"} className="item-thumb" key={index} value={index} onClick={(e)=>{handleImageClick(e)}}> <img src={image.path}/></button>
                                        )
                                    })}
                                </div>
                            </article>
                        </aside>
                        <main className="col-sm-6">
                            <article className="content-body body-content">
                                <h2 className="title">{productInfo.name}</h2>

                                <p>{productInfo.description}</p>
                                <ul className="list-normal cols-two">
                                    {
                                        productInfo.productWeight.weightList.map( (weightPrice, index) => {
                                            return(
                                                <li key={index}>{weightConverter(weightPrice.value)} for ${weightPrice.price.value}</li>
                                            )
                                            }
                                        )
                                    }
                                </ul>

                                <div className="h3 mb-4">
                                    <var className="price h4">${ price > 0 ? price :  props.productsFilter !== '' ? productInfo.productWeight.weightList[props.productsFilter].price.value : 0}</var>
                                </div>

                                {/*<div className="form-row">*/}
                                {/*    <div className="col-2">*/}
                                {/*        <select className="form-control">*/}
                                {/*            <option> 1</option>*/}
                                {/*            <option> 2</option>*/}
                                {/*            <option> 3</option>*/}
                                {/*        </select>*/}
                                {/*    </div>*/}

                                {/*    <div className="col-2">*/}
                                {/*        <select className="form-control">*/}
                                {/*            <option> Size</option>*/}
                                {/*            <option> XL</option>*/}
                                {/*            <option> MD</option>*/}
                                {/*            <option> XS</option>*/}
                                {/*        </select>*/}
                                {/*    </div>*/}
                                {/*<div className="row">*/}
                                {/*    <div className="form-group col-md flex-grow-0">*/}
                                {/*        <label>Quantity</label>*/}
                                {/*        <div className="input-group mb-3 input-spinner">*/}
                                {/*            <div className="input-group-append">*/}
                                {/*                <button className="btn btn-light" disabled={minus} type="button" id="button-minus" onClick={(e)=>{handleUpdateQuantity(e)}}> &minus; </button>*/}
                                {/*            </div>*/}
                                {/*            <input type="text" className="form-control" value={quantity} readOnly/>*/}
                                {/*            <div className="input-group-prepend">*/}
                                {/*                <button className="btn btn-light" type="button" id="button-plus" onClick={(e)=>{handleUpdateQuantity(e)}}> +</button>*/}
                                {/*            </div>*/}

                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*    <div className="form-group col-md flex-grow-0">*/}
                                {/*        <div className="input-group mb-3 input-select">*/}
                                {/*            <select className="form-control">*/}
                                {/*                <option>Weight</option>*/}
                                {/*                {   productInfo.productWeight.weightList.map((weightList, index) => {*/}
                                {/*                    return (*/}
                                {/*                        <option> {weightList.value}{weightList.productWeightBase}</option>*/}
                                {/*                    )*/}
                                {/*                })}*/}
                                {/*            </select>*/}
                                {/*        </div>*/}
                                {/*        <div className="col">*/}
                                {/*            <button className="btn  btn-primary w-100" onClick={()=>{handleClick()}}> Add to cart <i className="fas fa-shopping-cart"></i> </button>*/}
                                {/*        </div>*/}
                                {/*    </div>*/}
                                {/*</div>*/}
                                <div className="form-row" style={{display: 'block'}}>
                                    <div className="col-2">
                                        <div className="form-group col-md flex-grow-0">
                                            <label>Quantity</label>
                                            <div className="input-group mb-3 input-spinner">
                                                <div className="input-group-append">
                                                    <button className="btn btn-light" disabled={minus} type="button" id="button-minus" onClick={(e)=>{handleUpdateQuantity(e)}}> &minus; </button>
                                                </div>
                                                <input type="text" className="form-control" value={quantity} readOnly/>
                                                <div className="input-group-prepend">
                                                    <button className="btn btn-light" type="button" id="button-plus" onClick={(e)=>{handleUpdateQuantity(e)}}> +</button>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <div className="col">
                                        <select className="form-control" onChange={(e)=>{handleChangeSelect(e)}} value={weightIndex === '' ? props.productsFilter : weightIndex}>
                                            <option>Select weight</option>
                                            {   productInfo.productWeight.weightList.map((weightList, index) => {
                                                return (
                                                    <option key={index} value={index}> {weightConverter(weightList.value)}</option>
                                                )
                                            })}
                                            </select>
                                    </div>
                                    <div className="col">
                                        <button className="btn  btn-primary w-100" onClick={()=>{handleClick()}} disabled={props.productsFilter === '' && price === 0}> Add to cart <i className="fas fa-shopping-cart"></i> </button>
                                    </div>
                                </div>

                            </article>
                        </main>
                    </div>
                </div>
                </div>
            </div>
        </div>

    )
}

export default ProductDetails;