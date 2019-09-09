/**
 * @fileoverview gRPC-Web generated client stub for 
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!



const grpc = {};
grpc.web = require('grpc-web');


var ping_ping_request_pb = require('../ping/ping_request_pb.js')

var ping_ping_response_pb = require('../ping/ping_response_pb.js')
const proto = require('./ping_service_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.PingServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'binary';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

  /**
   * @private @const {?Object} The credentials to be used to connect
   *    to the server
   */
  this.credentials_ = credentials;

  /**
   * @private @const {?Object} Options for the client
   */
  this.options_ = options;
};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?Object} options
 * @constructor
 * @struct
 * @final
 */
proto.PingServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options['format'] = 'binary';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

  /**
   * @private @const {?Object} The credentials to be used to connect
   *    to the server
   */
  this.credentials_ = credentials;

  /**
   * @private @const {?Object} Options for the client
   */
  this.options_ = options;
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.ping.PingRequest,
 *   !proto.ping.PingResponse>}
 */
const methodDescriptor_PingService_ping = new grpc.web.MethodDescriptor(
  '/PingService/ping',
  grpc.web.MethodType.UNARY,
  ping_ping_request_pb.PingRequest,
  ping_ping_response_pb.PingResponse,
  /** @param {!proto.ping.PingRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  ping_ping_response_pb.PingResponse.deserializeBinary
);


/**
 * @const
 * @type {!grpc.web.AbstractClientBase.MethodInfo<
 *   !proto.ping.PingRequest,
 *   !proto.ping.PingResponse>}
 */
const methodInfo_PingService_ping = new grpc.web.AbstractClientBase.MethodInfo(
  ping_ping_response_pb.PingResponse,
  /** @param {!proto.ping.PingRequest} request */
  function(request) {
    return request.serializeBinary();
  },
  ping_ping_response_pb.PingResponse.deserializeBinary
);


/**
 * @param {!proto.ping.PingRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.Error, ?proto.ping.PingResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.ping.PingResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.PingServiceClient.prototype.ping =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/PingService/ping',
      request,
      metadata || {},
      methodDescriptor_PingService_ping,
      callback);
};


/**
 * @param {!proto.ping.PingRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.ping.PingResponse>}
 *     A native promise that resolves to the response
 */
proto.PingServicePromiseClient.prototype.ping =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/PingService/ping',
      request,
      metadata || {},
      methodDescriptor_PingService_ping);
};


module.exports = proto;

