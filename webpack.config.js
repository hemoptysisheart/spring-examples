/**
 * see https://webpack.js.org/configuration/
 */

var path = require("path");

module.exports = {
  mode: "development",
  entry: "./src/main/webpack/grpc-web-client.js",
  output: {
    path: path.resolve(__dirname, "./src/main/resources/js"),
    filename: "grpc-web-client.js",
    libraryTarget: "commonjs"
  }
};
