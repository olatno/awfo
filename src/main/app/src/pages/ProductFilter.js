import React, {Component} from "react";
import axios from 'axios'
import update from 'react-addons-update'
import SideBarFilterComponents from "../sideFilter/SideBarFilterComponents";
import Header from "../structure/Header";
import Footer from "../structure/Footer";
import BannerSmall from "../structure/BannerSmall";
import ProductComponentSmall from "../ecommerce/ProductComponentSmall";
import ProductComponentLarge from "../ecommerce/ProductComponentLarge";
import Pagination from "../structure/Pagination";
import ProductDetails from "../ecommerce/ProductDetails";


class ProductFilter extends Component {
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
            },
            view:'Grid view',
            loading: false,
            currentPage: 1,
            postsPerPage: 9,
            productDetailsDialog:false,
            productInfoDetails:'',
            productIndex:0,
            weightValueIndex:''
        }
    }

     componentDidMount() {
         this.getSearchArray() !== '' ? this.productCategoryAndGroup() : this.allProducts()
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
                if(key === 'price'){
                    return filterArray[key].find(filter => {
                            return item['productWeight'].weightList.find((prices, index) => {
                                return (prices.price.value).toString() === filter;
                            })
                    })
                }
                if(key === 'productWeight'){
                    return filterArray[key].find(filter => {
                        return item[key].weightList.find((weight, index) => {
                            return (weight.value).toString() === filter;
                         })
                    })
                }
            });
        })
    }

    getSearchArray(){
        let searchResult = '';
        this.props.location.search.split('?').forEach(result => {if(result !== undefined && result !== ''){searchResult = result}})
        return searchResult
    }

    productCategoryAndGroup = () => {
        let arrayResult = this.getSearchArray();
        const PRODUCT_REST_API_URL = '/awf/ecommerce/rest/products/'+arrayResult.replace(/%20/g, " ");
        const fetchProductCategoryAndGroup = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(PRODUCT_REST_API_URL)
                console.log(res.data)
                this.setState({products: res.data});
            }catch (err){
                console.log(err)
            }

            this.setState({loading:false})
        }
        fetchProductCategoryAndGroup().then()
    }

    allProducts = () => {
        const PRODUCT_REST_API_URL = '/awf/ecommerce/rest/products';
        const fetchProducts = async () => {
            this.setState({loading:true})
            try {
                const res = await axios.get(PRODUCT_REST_API_URL)
                this.setState({products: res.data});
            }catch (err){
              console.log(err)
            }

            this.setState({loading:false})
        }
         fetchProducts().then()
    }

    priceArrayValues = (itemValue) =>{
        let weightArrays = itemValue['productWeight'].weightList;
        console.log(weightArrays)
        let priceArray = []
        weightArrays.forEach( weights => {
            priceArray.push(weights.price.value)
        })
        return priceArray
    }
    handleProductComponentSwitch = (event) =>{
        let value = event.currentTarget.dataset.originalTitle
        this.setState({view : value})
        value === 'Grid view' ? this.setState({postsPerPage : 9}) : this.setState({postsPerPage : 4})
    }

    handlePaginationClick = (e, pageNumber) =>{
        e.preventDefault()
        this.setState({currentPage:pageNumber})
    }

    handleProductDetails = (productInfo, index) => {
        if(productInfo !== undefined) {
            this.setState({productInfoDetails: productInfo})
            this.findFilterIndex(productInfo)
        }
        this.setState({productDetailsDialog:!this.state.productDetailsDialog})
    }

    findFilterIndex = (productInfo) => {
        let productsFilter = this.state.filters
        let weightArray = productsFilter['productWeight']
        let priceArray = productsFilter['price']
        if(weightArray.length === 0) {
            let priceSortArray = priceArray.sort().reverse()
            priceSortArray.sort().forEach((priceValue) => {
                productInfo.productWeight.weightList.forEach((weightList,index) => {
                    if(weightList.price.value.toString() === priceValue){
                        this.setState({weightValueIndex:index})
                    }
                })
            })
        }else {
            let weightSortArray = weightArray.sort().reverse()
            weightSortArray.forEach((weightValue) => {
                productInfo.productWeight.weightList.forEach((weightList, index) => {
                    if(weightList.value.toString() === weightValue){
                        this.setState({weightValueIndex:index})
                    }
                })
            })
        }
    }

    render() {
        const indexOfLastPost = this.state.currentPage * this.state.postsPerPage
        const indexOfFirstPost = indexOfLastPost - this.state.postsPerPage


        const products = this.state.products
        const filteredProductArr = this.state.filteredProductArray
        const productFiltered = filteredProductArr.length === 0 ? products : filteredProductArr
        const productFilteredSlice = productFiltered.slice(indexOfFirstPost, indexOfLastPost)
        const ProductComponents = this.state.view === 'List view' ? ProductComponentLarge  : ProductComponentSmall
        const filterProductSize = this.state.filteredProductArray.length
        return (
            <div>
                <Header/>
                <BannerSmall/>
                <section className="section-content padding-y">
                    <div className="container">
                        <div className="row">
                            {this.state.loading ? <h2>Loading</h2> : ''}
                            <SideBarFilterComponents repos={products} handleCalledBackFromParent={this.handleCalledBackFromFilter}/>
                            <main className="col-md-9">
                                <header className="border-bottom mb-4 pb-3">
                                    <div className="form-inline">
                                        <span className="mr-md-auto">{filterProductSize !== 0 ? filterProductSize : products.length} Items found </span>
                                        <select className="mr-2 form-control">
                                            <option>Latest items</option>
                                            <option>Trending</option>
                                            <option>Most Popular</option>
                                            <option>Cheapest</option>
                                        </select>
                                        <div className="btn-group">
                                                <a href="#" className={`btn  btn-outline-secondary ${this.state.view === 'Grid view' ? 'active' : ''}`}  data-toggle="tooltip"
                                               title="Grid view" onClick={(e) => this.handleProductComponentSwitch(e)}>
                                                <i className="fa fa-th"></i></a>
                                            <a href="#" className={`btn  btn-outline-secondary ${this.state.view === 'List view' ? 'active' : ''}`}
                                               data-toggle="tooltip" title="List view" onClick={(e) => this.handleProductComponentSwitch(e)}>
                                                <i className="fa fa-bars"></i></a>
                                        </div>
                                    </div>
                                </header>
                                <ProductComponents repos={productFilteredSlice} productDetails={this.handleProductDetails} />
                                {this.state.productDetailsDialog ? <ProductDetails productInfo={this.state.productInfoDetails} productDetailsToggle={this.handleProductDetails}
                                                                                    productsFilter={this.state.weightValueIndex}/> : null}

                                <Pagination productsPerPage={this.state.postsPerPage}
                                            totalProducts={productFiltered.length}
                                            paginate={this.handlePaginationClick}
                                            currentProductPage={this.state.currentPage}/>
                            </main>
                        </div>
                    </div>
                </section>
                <Footer/>
            </div>
        )
    }
}
export default ProductFilter;