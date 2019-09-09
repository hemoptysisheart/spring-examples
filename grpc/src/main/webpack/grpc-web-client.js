const PingRequest = require("./ping/ping_request_pb.js");
console.log("PingRequest", PingRequest);

const PingResponse = require("./ping/ping_response_pb.js");
console.debug("PingResponse", PingResponse);

const PingServiceClient = require("./ping/ping_service_grpc_web_pb.js");
console.debug("PingServiceClient", PingServiceClient);

const pingService = new PingServiceClient("http://localhost:6565");
console.debug("pingService", pingService);

let request = new PingRequest();
console.debug("request", request);

pingService.ping(request, {}, function (err, response) {
  console.debug("pingService.ping callback", "err", err, "response", response);
});
