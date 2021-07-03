import React, {useState} from 'react';
const BrandComponent = (props) => {

    const [brandActive, setActive] = useState([]);
    const { brands } = props;

    if (!brands || brands.length === 0) return '';
    const createFilterBrandData = (brand, index) =>{
        let tempActiveArray = [...brandActive];
        brandActive.forEach((element) => {
            if (Object.keys((element))[0] === brand) {
                if (Object.values(element)[0] === 'productCode') {
                    tempActiveArray[index][brand] = '';
                }else{
                    tempActiveArray[index][brand] = 'productCode';
                }
            }
        })
        setActive(tempActiveArray)
        props.handleCalledBackSideFilter({brandActive})
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

    if(brands) {
        if(brandActive.length === 0) {
            setActive(
                brands.map((brand) => {
                    return {[brand] : ''}
                })
            )
        }
        return (
            <div className="filter-group">
                <header className="card-header">
                    <a href="#" data-toggle="collapse" data-target="#collapse_2" aria-expanded="true" className="">
                        <i className="icon-control fa fa-chevron-down"></i>
                        <h6 className="title">Brands</h6>
                    </a>
                </header>
                <div className="filter-content collapse show" id="collapse_2">
                    <div className="card-body">
                    {brands.map((brand, index) => {
                        return (
                            <div className="form-inline d-flex align-items-center py-1" key={brand}>
                                <label className={`tick ${checkActive (brandActive, brand)}`}  onChange={(e)=>{createFilterBrandData(brand, index)}} >{brand} <input type="checkbox"  /> <span className="check"></span> </label>
                            </div>
                            // <div>
                            //     <label className={`custom-control custom-checkbox ${checkActive (brandActive, brand)}`} onChange={(e)=>{createFilterBrandData(brand, index)}}>
                            //     <input type="checkbox" className="custom-control-input"/>
                            //         <div className="custom-control-label">{brand}</div>
                            //     </label>
                            // </div>
                        );
                    })}
                    </div>
                </div>
            </div>
        );
    }
};
export default BrandComponent;
