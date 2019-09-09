package com.github.hemoptysisheart.spring.example.grpc.api;

import com.github.hemoptysisheart.spring.example.grpc.api.message.PingRequestProto;
import com.github.hemoptysisheart.spring.example.grpc.api.message.PingResponseProto;
import com.github.hemoptysisheart.spring.example.grpc.api.stub.PingServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author justburrow
 * @since 2019/09/01
 */
@GRpcService
public class PingApi extends PingServiceGrpc.PingServiceImplBase {
  private static final Logger log = getLogger(PingApi.class);

  @PostConstruct
  private void postConstruct() {
    log.info("ping api initialized.");
  }

  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
  // com.github.hemoptysisheart.spring.example.grpc.grpc.PingServiceGrpc.PingServiceImplBase
  /////////////////////////////////////////////////////////////////////////////////////////////////////////////////// @Override
  @Override
  public void ping(PingRequestProto.PingRequest request, StreamObserver<PingResponseProto.PingResponse> response) {
    if (log.isTraceEnabled())
      log.trace("args : request={}, response={}", request, response);

    response.onNext(PingResponseProto.PingResponse.newBuilder()
        .setTimestamp(System.currentTimeMillis())
        .build());
    response.onCompleted();

    super.ping(request, response);
  }
}
