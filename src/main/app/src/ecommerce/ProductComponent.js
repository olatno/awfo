import React from 'react';
const ProductComponent = (props) => {
    const { repos } = props;
    if (!repos || repos.length === 0) return <p>No repos, sorry</p>;
    return (
        <section id="products">
            <div className="container py-3">
                <div className="row">
                    {repos.map((repo, index) => {
                        return (
                            <div className="col-lg-4 col-md-6 col-sm-10 offset-md-0 offset-sm-1" key={index}>
                                <div className="card"><img className="card-img-top" src={repo.gallery.images[0].path} />
                                    <div className="card-body">
                                        <h6 className="font-weight-bold pt-1">{repo.name}</h6>
                                        <div className="text-muted description">{repo.description} {repo.productWeight.value}{repo.productWeight.productWeightBase}</div>
                                        <div className="d-flex align-items-center product"><span className="fas fa-star"></span>
                                            <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="far fa-star"></span></div>
                                        <div className="d-flex align-items-center justify-content-between pt-3">
                                            <div className="d-flex flex-column">
                                                <div className="h6 font-weight-bold">${repo.price.value}</div>
                                            </div>
                                            <div className="btn btn-primary">Buy now</div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        );
                    })}
                </div>
            </div>
        </section>
    );
};
export default ProductComponent;
