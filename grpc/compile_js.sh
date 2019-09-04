#!/usr/bin/env bash

PROTO_DIR="src/main/proto"
JS_OUT_DIR="src/main/resources/files/js/"

[ -d $JS_OUT_DIR ] || mkdir -p $JS_OUT_DIR

echo "before :"
gls -l --time-style=full-iso $JS_OUT_DIR/ping
rm -rf $JS_OUT_DIR/ping

for PROTO in $(ls $PROTO_DIR/ping)
do
    protoc -I=$PROTO_DIR ping/$PROTO --js_out=import_style=commonjs:$JS_OUT_DIR
    protoc -I=$PROTO_DIR ping/$PROTO --grpc-web_out=import_style=commonjs+dts,mode=grpcweb:$JS_OUT_DIR
done

echo
echo "after :"
gls -l --time-style=full-iso $JS_OUT_DIR/ping

echo
echo "git status :"
git status $JS_OUT_DIR/ping

cd $JS_OUT_DIR
npm clean-install
npx webpack grpc.js
