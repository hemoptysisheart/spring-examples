#!/usr/bin/env bash

PROTO_DIR="src/main/proto"
JS_OUT_DIR="src/main/resources/files/js/grpc"

[ -d $JS_OUT_DIR ] || mkdir -p $JS_OUT_DIR

for PROTO in $(ls $PROTO_DIR/ping)
do
    protoc -I=$PROTO_DIR ping/$PROTO --js_out=import_style=commonjs:$JS_OUT_DIR
    protoc -I=$PROTO_DIR ping/$PROTO --grpc-web_out=import_style=commonjs,mode=grpcwebtext:$JS_OUT_DIR
done
