import React,  { useState} from 'react';
import {weightConverter} from '../helpers/Utils.js'

const WeightComponent = (props) => {

    const [weightActive, setActive] = useState([]);
    const { weights } = props;

    if (!weights || weights.length === 0) return '';

    const createFilterWeightData = (weight, index) =>{
        let tempActiveArray = [...weightActive];
        weightActive.forEach((element) => {
            if (Object.keys((element))[0] === weight.toString()) {
                if (Object.values(element)[0] === 'productWeight') {
                    tempActiveArray[index][weight] = '';
                }else{
                    tempActiveArray[index][weight] = 'productWeight';
                }
            }
        })
        setActive(tempActiveArray)
        props.handleCalledBackSideFilter({weightActive})
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

    // const weightConverter = (value) =>{
    //   let convert = value/1000
    //   let converted = ''
    //     converted = convert >= 1 ? convert+'kg' : value+'g'
    //   return converted;
    // }

    if(weights) {
        if(weightActive.length === 0) {
            setActive(
                weights.map((weight) => {
                    return {[weight.value] : ''}
                })
            )
        }
        return (
            <div className="filter-group">
                <header className="card-header">
                    <a href="#" data-toggle="collapse" data-target="#collapse_4" aria-expanded="true" className="">
                        <i className="icon-control fa fa-chevron-down"></i>
                        <h6 className="title">Weights</h6>
                    </a>
                </header>
                <div className="filter-content collapse show" id="collapse_4">
                    <div className="card-body">
                    {weights.map((weight, index) => {
                        return (
                            <div className="form-inline d-flex align-items-center py-1" key={weight.value}>
                                <label className={`tick ${checkActive (weightActive, weight.value)}`}  onChange={(e)=>{createFilterWeightData(weight.value, index)}} >{weightConverter(weight.value)} <input type="checkbox"  /> <span className="check"></span> </label>
                            </div>
                        // <div key={weight.value}>
                        //     <label className={`custom-control custom-checkbox ${checkActive (weightActive, weight.value)}`} onChange={(e)=>{createFilterWeightData(weight.value, index)}}>
                        //         <input type="checkbox" className="custom-control-input"/>
                        //         <div className="custom-control-label">{weight.value}{weight.productWeightBase}</div>
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
export default WeightComponent;
