#!/usr/bin/env bash

REPOSITORY_ROOT=$(git rev-parse --show-toplevel)
EXAMPLE_ROOT="$REPOSITORY_ROOT/grpc"
echo "REPOSITORY ROOT PATH : $REPOSITORY_ROOT"
echo "EXAMPLE ROOT PATH : $EXAMPLE_ROOT"
echo

# envoy proxy   #######################################################################################################
cd ${EXAMPLE_ROOT}/envoy
docker build -t helloworld/envoy .
echo
docker run -d --rm -p 8080:8080 -p 9901:9901 --network=host --name grpc_envoy helloworld/envoy
echo
docker container ls --all

# spring boot   #######################################################################################################
BOOT_JAR_DIR="$EXAMPLE_ROOT/build/libs"

cd ${EXAMPLE_ROOT}
./gradlew bootJar
cd ${BOOT_JAR_DIR}
./grpc-0.0.1.SNAPSHOT.jar &> /dev/null &

SPRING_BOOT_PID=$(cat ${BOOT_JAR_DIR}/application.pid)
echo
echo "SPRING BOOT PID : $SPRING_BOOT_PID"
