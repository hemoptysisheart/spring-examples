const {a} = require("./ping/ping_request_pb.js");
console.log(a);

const {b, c} = require("./ping/ping_response_pb.js");
console.debug(b, c);

const {d, e} = require("./ping/ping_service_grpc_web_pb.js");
console.debug(d, e);

// const pingService = new PingServiceClient("http://localhost:6565");
// console.debug("pingService", pingService);
//
// let request = new PingRequest();
// console.debug("request", request);
//
// pingService.ping(request, {}, function (err, response) {
//   console.debug("pingService.ping callback", "err", err, "response", response);
// });
