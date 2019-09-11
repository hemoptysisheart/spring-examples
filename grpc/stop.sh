#!/usr/bin/env bash

cd envoy
docker container stop grpc_envoy
docker container ls --all
