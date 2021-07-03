import Input from "./Input"
const CheckBox = (props) => {

    return( <div>
            <label for={props.name} className="form-label">{props.title}</label>
            <div className="checkbox-group">
                {props.options.map(option => {
                    return (
                        <label key={option}>
                            <Input
                                className="form-checkbox"
                                id = {props.name}
                                name={props.name}
                                onChange={props.handleChange}
                                value={option}
                                checked={ props.selectedOptions.indexOf(option) > -1 }
                                type="checkbox" /> {option}
                        </label>
                    );
                })}
            </div>
        </div>
    );

}
export default CheckBox;