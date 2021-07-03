import React,  { useState} from 'react';
const GroupComponent = (props) => {

    const [groupActive, setActive] = useState([]);
    const { groups } = props;

    if (!groups || groups.length === 0) return '';

    const createFilterGroupData = (id, index) =>{
        let tempActiveArray = [...groupActive];
        groupActive.forEach((element) => {
            if (Object.keys((element))[0] === id) {
                if (Object.values(element)[0] === 'productGroup') {
                    tempActiveArray[index][id] = '';
                }else{
                    tempActiveArray[index][id] = 'productGroup';
                }
            }
        })
        setActive(tempActiveArray)
        props.handleCalledBackSideFilter({groupActive})
    }
    const checkActive = (value, key) => {
        if(value.length !== 0) {
            for (let j = 0; j < value.length; j++) {
                let keyValue = JSON.parse(
                    JSON.stringify(
                        value[j]
                    )
                )
                if(Object.keys(keyValue)[0] === key){
                    return keyValue[key] ? 'active' : ''
                }

            }
        }
    }

    if(groups) {
        const groupObj = Object.fromEntries(groups);
        if(groupActive.length === 0) {
            setActive(
                Object.entries(groupObj).map(([key, value]) => {
                    return {[key]: ''}
                })
            )
        }
        return (
            // <div className="filter-group">
            // <header className="card-header">
            //     <a href="#" data-toggle="collapse" data-target="#collapse_1" aria-expanded="true" className="">
            //         <i className="icon-control fa fa-chevron-down"></i>
            //         <h6 className="title">Product groups</h6>
            //     </a>
            // </header>
            // <div className="filter-content collapse show" id="collapse_1">
            //     <ul className="list-group">
            //         {Object.entries(groupObj).map(([key, value], index) =>
            //             <li className={`list-group-item list-group-item-action d-flex justify-content-between align-items-center category ${checkActive(groupActive, key)}`} onClick={ ((e) => createFilterGroupData(key, index))} key={key}> {key}  <span className="badge badge-primary badge-pill">{value}</span> </li>
            //         )}
            //     </ul>
            // </div>
            // </div>
            <div className="card mb-3">
                <div className="card-body">
                     <header className="card-header">
                         <a href="#" data-toggle="collapse" data-target="#collapse_1" aria-expanded="true" className="">
                             <i className="icon-control fa fa-chevron-down"></i>
                             <h6 className="title">Product groups</h6>
                         </a>
                     </header>
                    <div className="filter-content collapse show" id="collapse_1">
                    <ul className="list-menu">
                        {Object.entries(groupObj).map(([key, value], index) =>
                            <li className={`list-group-item ${checkActive(groupActive, key)}`} onClick={ ((e) => createFilterGroupData(key, index))} key={index}>{key} <a href="#"> <span className="badge badge-pill badge-light float-right">{value}</span></a></li>
                        )}
                    </ul>
                    </div>
                </div>
            </div>
        );
    }
};
export default GroupComponent;
