import React from 'react';
import {weightConverter} from "../helpers/Utils";
const ProductComponentSmall = (props) => {
    const { repos } = props;
    if (!repos || repos.length === 0) return <p>No repos, sorry</p>;

    const triggerProductDetailsPopup = (e, productInfo, index) => {
         props.productDetails(productInfo, index)
        e.preventDefault()
    }

    return (
        <div className="row">
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
                    <div className="col-md-4" key={index}>
                        <figure className="card card-product-grid">
                            <div className="img-wrap">
                                <img className="card-img-top" src={repo.gallery.images[0].path} />
                                    <a className="btn-overlay" href="#"><i className="fa fa-search-plus"></i> Quick view</a>
                            </div>
                            <figcaption className="info-wrap">
                                <div className="fix-height">
                                    <a href="#" className="title">{repo.description} -
                                        {repo.productWeight.weightList.map((weight, indexW) => {
                                            return (
                                                <span key={indexW}>{weightConverter(weight.value)} {repo.productWeight.weightList.length - 1 > indexW ? ',' : ''}</span>
                                            )
                                        })}</a>
                                    <div className="price-wrap mt-2">
                                        <span className="price">${repo.productWeight.weightList[0].price.value}</span> / <span>{weightConverter(repo.productWeight.weightList[0].value)}</span>
                                    </div>
                                </div>
                                <a href="#" className="btn btn-block btn-primary" onClick={(event) => triggerProductDetailsPopup(event, repo, index)}> Details </a>
                            </figcaption>
                        </figure>
                    </div>

                );
            })}
        </div>
    );
};
export default ProductComponentSmall;
