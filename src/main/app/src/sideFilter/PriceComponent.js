import React, {useState} from 'react';

const PriceComponent = (props) => {

    const [priceActive, setActive] = useState([]);
    const { prices } = props;

    if (!prices || prices.length === 0) return '';
    const createFilterPriceData = (price, index) =>{

        let tempActiveArray = [...priceActive];
        priceActive.forEach((element) => {
            if (Object.keys((element))[0] === price.toString()) {
                if (Object.values(element)[0] === 'price') {
                    tempActiveArray[index][price] = '';
                }else{
                    tempActiveArray[index][price] = 'price';
                }
            }
        })
        setActive(tempActiveArray)
        props.handleCalledBackSideFilter({priceActive})
    }

    const checkActive = (value, key) => {
        if(value.length !== 0) {
            for (let j = 0; j < value.length; j++) {
                let keyValue = JSON.parse(
                    JSON.stringify(
                        value[j]
                    )
                )
                if(Object.keys(keyValue)[0] === key.toString()){
                    return keyValue[key] ? 'active' : ''
                }

            }
        }
    }

    if(prices) {
        if(priceActive.length === 0) {
            setActive(
                prices.map((price) => {
                    return {[price] : ''}
                })
            )
        }
        return (
            <div className="filter-group">
                <header className="card-header">
                    <a href="#" data-toggle="collapse" data-target="#collapse_3" aria-expanded="true" className="">
                        <i className="icon-control fa fa-chevron-down"></i>
                        <h6 className="title">Prices</h6>
                    </a>
                </header>
                <div className="filter-content collapse show" id="collapse_3">
                <div className="card-body">
                    {prices.map((price, index) => {
                        return (
                            <div className="form-inline d-flex align-items-center py-1" key={price}>
                                <label className={`tick ${checkActive (priceActive, price)}`}  onChange={(e)=>{createFilterPriceData(price, index)}} >${price} <input type="checkbox"  /> <span className="check"></span> </label>
                            </div>
                            // <div>
                            // <label className={`custom-control custom-checkbox ${checkActive (priceActive, price)}`} onChange={(e)=>{createFilterPriceData(price, index)}}>
                            //     <input type="checkbox" className="custom-control-input"/>
                            //     <div className="custom-control-label">{price}</div>
                            // </label>
                            // </div>
                        );
                    })}
                </div>
                </div>
            </div>
        );
    }
};
export default PriceComponent;
