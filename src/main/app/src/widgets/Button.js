import React from 'react';
const Button = (props) => {

    return(
        <div className={props.class}>
            <button
                className={props.className}
                type = {props.type}
                style= {props.style}
                value={props.value}
                onClick= {props.action}>
                {props.title}
            </button>
        </div>)
}

export default Button;