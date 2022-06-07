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
                    presets: [
                        [
                            "@babel/preset-env",
                            {
                                useBuiltIns: "entry",
                                corejs: 3
                            }
                        ]
                    ]
                }
            }
        ]
    },
    output: {
        filename: "stylus4j.js"
    }
}