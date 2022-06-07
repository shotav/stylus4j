module.exports = {
    name: "stylus4j",
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
        filename: "[name].js"
    }
}