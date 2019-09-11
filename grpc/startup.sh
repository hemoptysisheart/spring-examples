#!/usr/bin/env bash

cd envoy
docker build -t helloworld/envoy .
docker run -d --rm -p 8080:8080 -p 9901:9901 --network=host --name grpc_envoy helloworld/envoy
docker container ls --all
