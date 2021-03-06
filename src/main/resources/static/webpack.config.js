const webpack = require("webpack");
const path = require("path");

// http://webpack.github.io/docs/configuration.html
module.exports = {
	entry:{
		main: "./src/App.ts",
	},

	// Outputs compiled bundle to `./web/js/main.js`
	output:{

        path: path.join(__dirname, 'bin'),
        publicPath: '/bin', // instead of publicPath: '/build/' 
        filename: '[name].js'
	},

	resolve: {
		extensions: [".ts", ".webpack.js", ".web.js", ".tsx", ".js"],
	},

	module:{
		// Test file extension to run loader
		rules: [
			{
				test: /\.(glsl|vs|fs)$/, 
				loader: "ts-shader-loader"
			},
			{
				test: /\.tsx?$/, 
				exclude: [/node_modules/, /tsOld/],
				loader: "ts-loader"
			}
		]
	},

	// Enables dev server to be accessed by computers in local network
	devServer: {
		host: "0.0.0.0",
		port: 8000,
		publicPath: "/src/",
		disableHostCheck: true,
        writeToDisk: true
	}
}