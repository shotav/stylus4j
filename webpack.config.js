module.exports = {
    target: "node",
    mode: "production",
    entry: "./src/main/javascript/stylus4j.js",
    module: {
        rules: [
            {
                test: /\.js$/,
                loader: "babel-loader",
                options: {
                    presets: [ "@babel/preset-env" ]
                }
            }
        ]
    },
    output: {
        filename: "stylus4j.js"
    }
}