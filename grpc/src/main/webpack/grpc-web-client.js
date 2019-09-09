const PingRequest = require("./ping/ping_request_pb");
console.log("PingRequest", PingRequest);

const PingResponse = require("./ping/ping_response_pb");
console.debug("PingResponse", PingResponse);

const PingService = require("./ping/ping_service_grpc_web_pb");
console.debug("PingService", PingService);
