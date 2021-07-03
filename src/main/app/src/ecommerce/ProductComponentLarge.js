import React from 'react';
import {weightConverter} from '../helpers/Utils.js'
const ProductComponentLarge = (props) => {
    const { repos } = props;
    if (!repos || repos.length === 0) return <p>No repos, sorry</p>;

    const triggerProductDetailsPopup = (e, productInfo) => {
         props.productDetails(productInfo)
        e.preventDefault()
    }

    return (
        <div>
            {repos.map((repo, index) => {
                return (
                    // <div className="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1" key={index}>
                    //     <div className="card"><img className="card-img-top" src={repo.gallery.images[0].path} />
                    //     <div className="card-body">
                    //         <h6 className="font-weight-bold pt-1">{repo.name}</h6>
                    //         <div className="text-muted description">{repo.description} {repo.productWeight.value}{repo.productWeight.productWeightBase}</div>
                    //         <div className="d-flex align-items-center product"><span className="fas fa-star"></span>
                    //             <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="far fa-star"></span></div>
                    //         <div className="d-flex align-items-center justify-content-between pt-3">
                    //             <div className="d-flex flex-column">
                    //                 <div className="h6 font-weight-bold">${repo.price.value}</div>
                    //             </div>
                    //             <div className="btn btn-primary">Buy now</div>
                    //         </div>
                    //     </div>
                    //     </div>
                    // </div>

                    <article className="card card-product-list" key={index}>
                        <div className="row no-gutters">
                            <aside className="col-md-3">
                                <a href="#" className="img-wrap">
                                    <span className="badge badge-danger"> NEW </span>
                                    <img className="card-img-top" src={repo.gallery.images[0].path} />
                                </a>
                            </aside>
                            <div className="col-md-6">
                                <div className="info-main">
                                    <a href="#" className="h5 title"> {repo.name} </a>
                                    <div className="rating-wrap mb-3">
                                        <ul className="rating-stars">
                                            <li style={{width:'80%'}} className="stars-active">
                                                <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                                <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                                <i className="fa fa-star"></i>
                                            </li>
                                            <li>
                                                <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                                <i className="fa fa-star"></i> <i className="fa fa-star"></i>
                                                <i className="fa fa-star"></i>
                                            </li>
                                        </ul>
                                        <div className="label-rating">7/10</div>
                                    </div>

                                    <p>{repo.description} - {repo.productWeight.weightList.map((weight, indexW) => {
                                        return (
                                            <span key={indexW}>{weightConverter(weight.value)} {repo.productWeight.weightList.length - 1 > indexW ? ',' : ''}</span>
                                        )
                                    })}</p>
                                </div>
                            </div>
                            <aside className="col-sm-3">
                                <div className="info-aside">
                                    <div className="price-wrap">
                                        <span className="price h5"> ${repo.productWeight.weightList[0].price.value}</span> / <span>{weightConverter(repo.productWeight.weightList[0].value)} </span>
                                        {/*<del className="price-old"> $198</del>*/}
                                    </div>
                                    <p className="text-success">Free shipping</p>
                                    <br/>
                                        <p>
                                            <a href="#" className="btn btn-primary btn-block" onClick={(event) => triggerProductDetailsPopup(event, repo)}> Details </a>
                                            <a href="#" className="btn btn-light btn-block"><i
                                                className="fa fa-heart"></i>
                                                <span className="text">Add to wishlist</span>
                                            </a>
                                        </p>
                                </div>
                            </aside>
                        </div>
                    </article>
                );
            })}
        </div>
    );
};
export default ProductComponentLarge;
