import * as grpcWeb from 'grpc-web';

import * as ping_ping_request_pb from '../ping/ping_request_pb';
import * as ping_ping_response_pb from '../ping/ping_response_pb';

export class PingServiceClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  ping(
    request: ping_ping_request_pb.PingRequest,
    metadata: grpcWeb.Metadata | undefined,
    callback: (err: grpcWeb.Error,
               response: ping_ping_response_pb.PingResponse) => void
  ): grpcWeb.ClientReadableStream<ping_ping_response_pb.PingResponse>;

}

export class PingServicePromiseClient {
  constructor (hostname: string,
               credentials?: null | { [index: string]: string; },
               options?: null | { [index: string]: string; });

  ping(
    request: ping_ping_request_pb.PingRequest,
    metadata?: grpcWeb.Metadata
  ): Promise<ping_ping_response_pb.PingResponse>;

}

