const {PingRequest} = require('./ping/ping_request_pb');
const {PingResponse} = require('./ping/ping_response_pb');
const {PingServiceClient} = require('./ping/ping_service_grpc_web_pb');

let client = new PingServiceClient('http://localhost:5656');

$(document).ready(function () {
  console.log("jQuery ready!");

  $("#ping-bt").click(function (evt) {
    console.debug("evt", evt)
    let request = new PingRequest();
    client.ping(request, {}, function (err, response) {
      console.debug("ping callback", "err", err, "response", response);
    });
  });
});
