const {PingRequest} = require('./ping/ping_request_pb');
const {PingResponse} = require('./ping/ping_response_pb');
const {PingServiceClient} = require('./ping/ping_service_grpc_web_pb');

let client = new PingServiceClient('http://localhost:5656');

let request = new PingRequest();

client.ping(request, {}, function (err, response) {
  console.debug("err", err, "response", response);
});
