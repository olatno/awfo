import React,  { useState} from 'react';
import GroupComponent from "./GroupComponent";
import WeightComponent from "./WeightComponent";
import BrandComponent from "./BrandComponent";
import PriceComponent from "./PriceComponent";

const SideBarFilterComponentsUI = (props) => {
    const [checked, setChecked] = useState(true);
    const [priceList, setPriceList] = useState('')
    const { repos } = props;

    if (!repos || repos.length === 0) return <p>No repos, sorry</p>;
    const productBrands =  repos.map(brand => brand.productCode.brand).map(brandName => brandName.name).sort();
    const brands = productBrands.filter(function(item, pos) {
        return productBrands.indexOf(item) === pos;
    })
    const productGroups =  repos.map(group => group.productCode.productGroup).filter(categoryValue => categoryValue.category.name).map(nameValue => nameValue.name);
    const groupMap = productGroups.reduce((acc, e) => acc.set(e, (acc.get(e) || 0) + 1), new Map());

    const productPrice =  repos.map(priceValues => priceValues.productWeight.weightList[0].price.value).sort();
    const productWeightList =  repos.map(priceValues => priceValues.productWeight.weightList)
    const productPrices = productWeightList.map((row, index) => {
        return row.map((column) => {
            return column.price.value
        })
    })
    const priceSet = new Set()
    const weightSet = new Set()
    const weightsArray = [];
    productWeightList.forEach( row => {
        row.forEach(column =>
        {
            priceSet.add(column.price.value);
            if(!weightSet.has(column.value)){
                weightsArray.push(column);
            }
            weightSet.add(column.value)
        })
    })
    // const prices = productPrices.filter(function(item, pos) {
    //     return productPrices.indexOf(item) === pos;
    // })
    const prices = Array.from(priceSet);

    // const printWeightList = () =>{
    //     let priceListValue = ''
    //
    //     return priceListValue
    // }
    // setPriceList(printWeightList)

    const productWeights =  repos.map(weights => weights.productWeight.weightList[0]).filter((obj, pos, arr) => {
        return arr.map(mapObj =>
            mapObj.value).indexOf(obj.value) === pos;
    });
    const weights = weightsArray.sort((a,b) => a.value - b.value);


    return (

           //
            // <section id="sidebar">
            //     <GroupComponent  groups = {groupMap} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
            //     <BrandComponent  brands = {brands} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
            //     <PriceComponent  prices = {prices} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
            //     <WeightComponent  weights = {weights} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
            //     <div className="py-3">
            //         <h5 className="font-weight-bold">Rating</h5>
            //         <form className="rating">
            //             <div className="form-inline d-flex align-items-center py-2">
            //                 <label className="tick"><span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <input type="checkbox"/> <span className="check"></span></label>
            //             </div>
            //             <div className="form-inline d-flex align-items-center py-2">
            //                 <label className="tick"> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="far fa-star px-1 text-muted"></span> <input type="checkbox"/> <span className="check"></span> </label>
            //             </div>
            //             <div className="form-inline d-flex align-items-center py-2">
            //                 <label className="tick"><span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <input type="checkbox"/> <span className="check"></span> </label>
            //             </div>
            //             <div className="form-inline d-flex align-items-center py-2">
            //                 <label className="tick"><span className="fas fa-star"></span> <span className="fas fa-star"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <input type="checkbox"/> <span className="check"></span> </label>
            //             </div>
            //             <div className="form-inline d-flex align-items-center py-2">
            //                 <label className="tick"> <span className="fas fa-star"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <span className="far fa-star px-1 text-muted"></span> <input type="checkbox"/> <span className="check"></span> </label>
            //             </div>
            //         </form>
            //     </div>
            // </section>
        <aside className="col-md-3">
            <div className="card">
                <GroupComponent  groups = {groupMap} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
                <BrandComponent  brands = {brands} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
                <PriceComponent  prices = {prices.sort()} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
                <WeightComponent  weights = {weights.sort()} handleCalledBackSideFilter={props.handleCalledBackFromParent}/>
            </div>
        </aside>

    )

}
export default SideBarFilterComponentsUI;