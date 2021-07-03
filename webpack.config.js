var path = require('path');
var glob = require("glob");
module.exports = {
    entry:  './src/main/app/src/App.js',
    devtool: 'source-map',
    cache: true,
    mode: 'development',
    output: {
        path: __dirname,
        filename: './src/main/resources/static/js/bundle.js'
    },
    module: {
        rules: [
            {
                test: path.join(__dirname, '.'),
               // test:  /\.js$|jsx/,
                exclude: /(node_modules)/,
                use: [{
                    loader: 'babel-loader',
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"],
                        plugins: [
                            [
                                "@babel/plugin-proposal-class-properties"
                            ],
                            [
                                "@babel/plugin-transform-runtime"
                            ]
                        ]
                    }
                }]
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            }
        ]
    }
};