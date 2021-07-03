import React from "react";
import axios from 'axios'
import update from 'react-addons-update'
import ProductComponent from './ProductComponent'
import SideBarFilterComponents from "../sideFilter/SideBarFilterComponents";
import ReactDOM from "react-dom";


class RecentlyAdded extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            products: [],
            filteredProductArray: [],
            filters : {
                productGroup:[],
                productCode:[],
                price:[],
                productWeight:[]
            }
        }
    }

     componentDidMount() {
        const PRODUCT_REST_API_URL = '/awf/ecommerce/rest/product';
        axios.get(PRODUCT_REST_API_URL).then((repository) => {
            const allRepos = repository.data;
            this.setState({products: allRepos});
        });
    }

    handleCalledBackFromFilter = (filteredProducts) =>{

        let currentFilters = this.state.filters
        let productFilterType = Object.keys(filteredProducts)[0];

        if(productFilterType === 'groupActive'){
            this.createFilterObjects(Object.values(filteredProducts)[0], currentFilters.productGroup, 'productGroup');
        }else if(productFilterType === 'brandActive'){
            this.createFilterObjects(Object.values(filteredProducts)[0], currentFilters.productCode, 'productCode');

        }else if(productFilterType === 'priceActive'){
            this.createFilterObjects(Object.values(filteredProducts)[0], currentFilters.price, 'price');
        }else if(productFilterType === 'weightActive'){
            this.createFilterObjects(Object.values(filteredProducts)[0], currentFilters.productWeight, 'productWeight');
        }
        let filterArrayValues  = this.filterProductArrays(this.state.products, currentFilters);
        this.setState({filteredProductArray: filterArrayValues});
    }

    createFilterObjects = (filteredProducts, currentFilters, activeFilter) =>{
        filteredProducts.forEach((element, index)  => {
                let productFilterValue = Object.values(element)[0]
                let productFilterKey = Object.keys(element)[0]
                let currentValue = this.state.filters
                 if (productFilterValue === '' && currentFilters.includes(productFilterKey)){
                     let indexRemove = currentValue[activeFilter].indexOf(productFilterKey)
                     let currentFilterValue = [...currentValue[activeFilter].slice(0, indexRemove), ...currentValue[activeFilter].slice(indexRemove + 1)];
                     currentValue[activeFilter] = currentFilterValue
                     this.setState({filters: currentValue})
                }else if(productFilterValue !== '' && !currentFilters.includes(productFilterKey)){
                     let newFilterArray = update(currentFilters, {$push: [productFilterKey]});
                     currentValue[activeFilter] = newFilterArray
                     this.setState({filters: currentValue})
                     console.log(newFilterArray)
                }

            }

        )
    }

    filterProductArrays = (products, filterArray) => {
        let filterKeys = Object.keys(filterArray);
        return products.filter(item => {
            return filterKeys.every(key => {
                // ignores an empty filter
                if (!filterArray[key].length) return true;
                if(key === 'productGroup'){return filterArray[key].find(filter => filter === item[key].name);}
                if(key === 'productCode'){return filterArray[key].find(filter => filter === item[key].brand.name);}
                if(key === 'price'){return filterArray[key].find(filter => filter === (item[key].value).toString());}
                if(key === 'productWeight'){return filterArray[key].find(filter => filter === (item[key].value).toString());}
            });
        })}

    render() {
        const products = this.state.products
        const filteredProductArr = this.state.filteredProductArray
        const productFiltered = filteredProductArr.length === 0 ? products : filteredProductArr
        return (
            <div className="content py-md-0 py-3">
                 <SideBarFilterComponents repos={products} handleCalledBackFromParent={this.handleCalledBackFromFilter}/>
                 <ProductComponent repos={productFiltered} />
            </div>
        )
    }
}
export default RecentlyAdded;
//ReactDOM.render(<RecentlyAdded />, document.getElementById("root"));
// export default RecentlyAdded;