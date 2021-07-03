import React from 'react';

const Input = (props) => {
    return (
        <div className = {props.class}>
            <label htmlFor={props.name} className="form-label">{props.title}</label>
            <input
                className = {props.className}
                id={props.id}
                name={props.name}
                type={props.type}
                value={props.value}
                onChange={props.handleChange}
                placeholder={props.placeholder}
                checked={props.checked}
            />
        </div>
    )
}
export default Input;