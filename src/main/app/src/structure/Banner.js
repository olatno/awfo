import React, {Component} from "react";
import Header from "./Header";

class Banner extends Component {
    constructor(props) {
        super(props);
    }

    render() {

        return(
            <section className="section-intro">

                <div className="intro-banner-wrap">
                    <img src="images/banners/1.jpg" className="w-100 img-fluid" />
                </div>

            </section>
        )
    }
}
export default Banner;