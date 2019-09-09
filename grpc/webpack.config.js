/**
 * see https://webpack.js.org/configuration/
 */

var path = require("path");

module.exports = {
  mode: "development",
  entry: "./src/main/webpack/grpc-web-client.js",
  output: {
    path: path.resolve(__dirname, "./src/main/resources/files/js"),
    filename: "app.js",
    libraryTarget: "commonjs"
  }
};