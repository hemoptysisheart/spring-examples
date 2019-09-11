#!/usr/bin/env bash

REPOSITORY_ROOT=$(git rev-parse --show-toplevel)
EXAMPLE_ROOT="$REPOSITORY_ROOT/grpc"
echo "REPOSITORY ROOT PATH : $REPOSITORY_ROOT"
echo "EXAMPLE ROOT PATH : $EXAMPLE_ROOT"
echo

# envoy proxy   #######################################################################################################
cd ${EXAMPLE_ROOT}/envoy
docker container stop grpc_envoy
echo
docker container ls --all

# spring boot   #######################################################################################################
BOOT_JAR_DIR="$EXAMPLE_ROOT/build/libs"
SPRING_BOOT_PID=$(cat ${BOOT_JAR_DIR}/application.pid)

echo
cd $BOOT_JAR_DIR
echo "SPRING BOOT PID : $SPRING_BOOT_PID"
kill -9 ${SPRING_BOOT_PID}
