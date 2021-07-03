import React, {Component} from "react";
import Header from "./Header";

class Pagination extends Component {

    render() {
        const pageNumbers = [];
        for(let i = 1; i <= Math.ceil(this.props.totalProducts / this.props.productsPerPage); i++){
            pageNumbers.push(i)
        }
        const next = this.props.currentProductPage + 1;
        const previous = this.props.currentProductPage - 1;
        return(
            <nav aria-label="Page navigation sample">
                <ul className="pagination">
                    <li className={`page-item ${this.props.currentProductPage > 1 ? '' : 'disabled'}`} ><a className="page-link" href="!#" onClick={(e) => this.props.paginate(e, previous)}>Previous</a></li>
                    {pageNumbers.map( number => (
                        <li key={number} className={`page-item ${this.props.currentProductPage === number ? 'active' : ''}`} ><a className="page-link" href="!#" title={number} onClick={(e) => this.props.paginate(e, number)}>{number}</a></li>
                    ))}
                    <li className={`page-item ${pageNumbers.length + 1 === next ? 'disabled' : ''}`}><a className="page-link" href="!#" onClick={(e) => this.props.paginate(e, next)}>Next</a></li>
                </ul>
            </nav>
        )
    }
}
export default Pagination;